package logic;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class ExcelReader {


    public void read(String excelFilePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();


        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;

                }
                System.out.print(" - ");
            }
            System.out.println();
        }

        workbook.close();
        inputStream.close();
        writeXLSXFile(1,1, excelFilePath);
    }

    public static void writeXLSXFile(int row, int col, String path) throws IOException {
        try {
            FileInputStream file = new FileInputStream(path);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = null;

            XSSFRow sheetrow = sheet.getRow(row);
            if(sheetrow == null){
                sheetrow = sheet.createRow(row);
            }
//Update the value of cell
            cell = sheetrow.getCell(col);
            if(cell == null){
                cell = sheetrow.createCell(col);
            }
            cell.setCellValue("Pass");

            file.close();

            FileOutputStream outFile =new FileOutputStream(new File(path));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

