package excelReader;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReader {
    public  static Workbook workbook = null;
    public  static Sheet sheet = null;
    public  static  int numberOfRows, numberOfCols;

    public  static  String [] getDataFromExecelFile(String filePath, int sheetNumber) throws Exception {
        String path = System.getProperty("user.dir")+filePath;
        String [] st = readExcel(path,sheetNumber);
        return st;
    }
    public static  String [] readExcel (String path, int sheetNumber) throws IOException, InvalidFormatException {
        String [] data = {};
        File file = new File (path);
        FileInputStream fis = new FileInputStream(file);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheetAt(sheetNumber);
        numberOfRows = sheet.getLastRowNum();
        numberOfCols = sheet.getRow(0).getLastCellNum();

        data = new String [numberOfRows +1];
        for (int I = 1;I<data.length; I++ ){
            Row rows = sheet.getRow(I);
            for(int j = 0; j<numberOfCols; j ++){
                Cell cell = rows.getCell(j);
                String cellData = getCellValue(cell);
                data [I]= cellData;
            }
        }
        return  data;
    }
    private  static  String getCellValue(Cell cell){
        Object value = null;
        int dataType = cell.getCellType();
        switch (dataType){
            case Cell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value= cell.getBooleanCellValue();
                break;
        }
        return value.toString();
    }

}
