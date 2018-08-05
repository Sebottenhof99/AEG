package logic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.*;

public class ExcelWriter {
    FileInputStream file;
    XSSFSheet sheet;
    XSSFWorkbook workbook;
    public void openFile(String path) throws IOException {

        file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheetAt(0);
    }


    public void writeXLSXFile(Point p, String value) {
             Cell cell;

            XSSFRow sheetrow = sheet.getRow(p.y);
            if (sheetrow == null) {
                sheetrow = sheet.createRow(p.y);
            }

            cell = sheetrow.getCell(p.x);
            if (cell == null) {
                cell = sheetrow.createCell(p.x);
            }
            cell.setCellValue(value);


    }

    public void closeFile(String storagePath) throws IOException {
        file.close();
        FileOutputStream outFile = new FileOutputStream(storagePath);
        workbook.write(outFile);
        outFile.close();
    }
}
