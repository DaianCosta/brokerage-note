package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.domain.entities.Movement;
import com.daiancosta.brokeragenote.domain.entities.constants.MovementConstant;
import com.daiancosta.brokeragenote.helpers.FormatHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
class FileMovementServiceImpl implements FileMovementService {
    private static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String SHEET = "Movimentação";

    public boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    @Override
    public List<Movement> mapData(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Movement> movements = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                final Movement movement = new Movement();
                final DataFormatter format = new DataFormatter();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            movement.setTypeTransaction(currentCell.getStringCellValue());
                            break;
                        case 1:
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            final LocalDate parseDate = LocalDate.parse(currentCell.getStringCellValue(), formatter);
                            movement.setDate(parseDate);
                            break;
                        case 2:
                            movement.setTypeOperation(currentCell.getStringCellValue());
                            break;
                        case 3:
                            movement.setTitleCode(setTitleCode(currentCell.getStringCellValue()));
                            movement.setDescription(currentCell.getStringCellValue());
                            break;
                        case 4:
                            movement.setInstitution(currentCell.getStringCellValue());
                            break;
                        case 5:
                            movement.setQuantity(FormatHelper
                                    .stringToBigDecimal(currentCell.getStringCellValue()));
                            break;
                        case 6:
                            movement.setPriceUnit(FormatHelper
                                    .stringToBigDecimal(format.formatCellValue(currentCell)));
                            break;
                        case 7:
                            movement.setPrice(FormatHelper
                                    .stringToBigDecimal(format.formatCellValue(currentCell)));
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                movements.add(movement);
            }
            workbook.close();
            return movements;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    private String setTitleCode(final String titleDescription) {

        final String[] itemArray = Arrays.stream(titleDescription.split("-"))
                .filter(it -> !it.equals(""))
                .toArray(String[]::new);

        if (titleDescription.contains(MovementConstant.OPTION)) {
            return itemArray[3];
        }
        return itemArray[0];
    }
}