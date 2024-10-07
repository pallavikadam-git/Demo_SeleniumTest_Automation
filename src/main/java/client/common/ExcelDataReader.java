package client.common;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelDataReader {
    XSSFWorkbook wb;

    public ExcelDataReader() {
        String filePath = System.getProperty("user.dir") + "/TestData/TestData.xlsx";
        FileInputStream file;
        try {
            file = new FileInputStream(filePath);
            wb = new XSSFWorkbook(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStringData(String sheetName, int row, int cell){
        return wb.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
    }

    public String getNumericDataAsString(String sheetName, int row, int cell){
        return String.valueOf((int)wb.getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue());
    }

    public int getNumericData(String sheetName, int row, int cell){
        return (int) wb.getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue();
    }

    public boolean getBooleanData(String sheetName, int row, int cell){
        return wb.getSheet(sheetName).getRow(row).getCell(cell).getBooleanCellValue();
    }
}
