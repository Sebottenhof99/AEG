package logic;

import bdo.PhoneCover;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;

public class ExcelReader {


    public ArrayList<PhoneCover> read(String excelFilePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        ArrayList<String> motive = new ArrayList();
        ArrayList<PhoneCover> phoneCover = new ArrayList();

        for (int i = 0; iterator.hasNext(); i++) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            String currentPhone = "";
            for (int j = 0; cellIterator.hasNext(); j++) {

                Cell cell = cellIterator.next();

                //speichert alle Motive
                if (i == 0) {
                    motive.add(cell.getStringCellValue());

                }
                //nimmt hier den Handynamen
                if (j == 0 && i != 0) {
                    if (cell.getCellTypeEnum() != CellType.STRING) {
                    } else {
                        currentPhone = cell.getStringCellValue();
                        continue;
                    }


                }

                //geht alle Kombinationen durch
                if (i != 0) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            phoneCover.add(new PhoneCover(currentPhone, motive.get(j)));
                            //   System.out.print(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (cell.getNumericCellValue() == 0) {
                                continue;
                            } else {
                                phoneCover.add(new PhoneCover(currentPhone, motive.get(j)));
                            }

                            break;

                    }
                }

            }

        }


        for (int i = 0; i < phoneCover.size(); i++) {
            System.out.println(phoneCover.get(i).getPhoneName() + " " + phoneCover.get(i).getMotive());
        }

        workbook.close();
        inputStream.close();
        return phoneCover;
    }



}





