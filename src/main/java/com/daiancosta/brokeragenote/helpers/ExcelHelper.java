package com.daiancosta.brokeragenote.helpers;

import com.daiancosta.brokeragenote.domain.entities.Business;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "Negociação";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Business> excelToBusiness(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Business> businesses = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Business business = new Business();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            business.setDate(currentCell.getStringCellValue());
                            break;
                        case 1:
                            business.setMovementType(currentCell.getStringCellValue());
                            break;
                        case 2:
                            business.setMarket(currentCell.getStringCellValue());
                            break;
                        case 3:
                            business.setDeadline(currentCell.getStringCellValue());
                            break;
                        case 4:
                            business.setInstitution(currentCell.getStringCellValue());
                            break;
                        case 5:
                            business.setTradingCode(currentCell.getStringCellValue());
                            break;
                        case 6:
                            business.setQuantity(currentCell.getNumericCellValue());
                            break;
                        case 7:
                            business.setPrice(currentCell.getNumericCellValue());
                            break;
                        case 8:
                            business.setTotal(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                businesses.add(business);
            }
            workbook.close();
            return businesses;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}