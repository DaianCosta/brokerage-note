package com.daiancosta.brokeragenote.services.negotiation;

import com.daiancosta.brokeragenote.domain.entities.Negotiation;
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
class FileNegotiationServiceImpl implements FileNegotiationService {
    private static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String SHEET = "Movimentação";

    public boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    @Override
    public List<Negotiation> mapData(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Negotiation> negotiations = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                final Negotiation negotiation = new Negotiation();
                final DataFormatter format = new DataFormatter();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            final LocalDate parseDate = LocalDate.parse(currentCell.getStringCellValue(), formatter);
                            negotiation.setDate(parseDate);
                            break;
                        case 1:
                            negotiation.setMovementType(currentCell.getStringCellValue());
                            break;
                        case 2:
                            negotiation.setMarket(currentCell.getStringCellValue());
                            break;
                        case 3:
                            if (!currentCell.getStringCellValue().equals("-")) {
                                final LocalDate parseDateDeadline = LocalDate.parse(currentCell.getStringCellValue(), formatter);
                                negotiation.setDeadline(parseDateDeadline);
                            }
                            break;
                        case 4:
                            negotiation.setInstitution(currentCell.getStringCellValue());
                            break;
                        case 5:
                            negotiation.setTitleCode(currentCell.getStringCellValue());
                            break;
                        case 6:
                            negotiation.setQuantity(FormatHelper
                                    .stringToBigDecimal(currentCell.getStringCellValue()));
                            break;
                        case 7:
                            negotiation.setPriceUnit(FormatHelper
                                    .stringToBigDecimal(format.formatCellValue(currentCell)));
                        case 8:
                            negotiation.setPrice(FormatHelper
                                    .stringToBigDecimal(format.formatCellValue(currentCell)));
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                negotiations.add(negotiation);
            }
            workbook.close();
            return negotiations;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    private String setTitleCode(final String titleDescription) {

        final String[] itemArray = Arrays.stream(titleDescription.split("-"))
                .filter(it -> !it.equals(""))
                .toArray(String[]::new);

        if (titleDescription.contains(MovementConstant.OPTION)) {
            return itemArray[1].trim();
        }
        return itemArray[0].trim();
    }
}