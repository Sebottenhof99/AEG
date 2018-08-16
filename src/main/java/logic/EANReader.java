package logic;

import bdo.PhoneCover;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EANReader {
    String path;
    FileInputStream inputStream;
    Workbook workbook;
    Sheet firstSheet;
    String newExcelPath;
    static ArrayList<String> EANS;

    public EANReader(String path) {
        this.path = path;
    }

    public boolean starttt(ArrayList<PhoneCover> list) throws IOException {
        openFile();
        boolean enoughEANS = isEnoughEANAvailable(list);
        if (!enoughEANS) {
            loadNewEANFile();
        }
        EANS = getEANs(list.size());
        return true;
    }

    private void loadNewEANFile() throws IOException {
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Neue EAN Datei auswählen");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("EAN - Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            newExcelPath = file.getPath();
            writeNewEans(newExcelPath);
        }
    }

    private void writeNewEans(String newExcelPath) throws IOException {
        FileInputStream inputNew = new FileInputStream(newExcelPath);
        Workbook workbookNew = new XSSFWorkbook(inputNew);
        Sheet firstSheetNew = workbookNew.getSheetAt(0);

        Iterator<Row> iterator = firstSheetNew.iterator();
        ArrayList<String> eans = new ArrayList();

        System.out.println("Start reading EANs from new file");
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String s = cell.getStringCellValue();
                if (s != null && !s.isEmpty()) {
                    eans.add(cell.getStringCellValue());
                }
            }
        }
        System.out.println("There are new eans: " + eans.size());



        int rowCounter = 0;
        Cell cell;
        System.out.println("Start writing new EANs into the file");
        for (int i = 0; i < eans.size();) {
            Row sheetrow = firstSheet.getRow(rowCounter);
            if (sheetrow == null) {
                sheetrow = firstSheetNew.createRow(rowCounter);
            }
            rowCounter++;
            cell = sheetrow.getCell(0);
            if (cell == null) {
                cell = sheetrow.createCell(0);
            }

            String valueOfCurrentCell = cell.getStringCellValue();
            if (valueOfCurrentCell == null || valueOfCurrentCell.isEmpty()) {
                cell.setCellValue(eans.get(i++));
            }
        }

        System.out.println("finish writing eans");
        inputNew.close();
        workbookNew.close();
        closeFile();
    }


    public void openFile() throws IOException {
        inputStream = new FileInputStream(new File(path));
        workbook = new XSSFWorkbook(inputStream);
        firstSheet = workbook.getSheetAt(0);

    }
    
    public ArrayList<String> getEANs(int necessaryAmount) throws IOException {
        int counter = 0;
        Iterator<Row> iterator = firstSheet.iterator();
        ArrayList<String> data = new ArrayList();
        System.out.println(necessaryAmount + " necessary");

        FileInputStream inputNew = new FileInputStream(path);
        Workbook workbookNew = new XSSFWorkbook(inputNew);


        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while( cellIterator.hasNext() ) {
                Cell cell = cellIterator.next();
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String s = cell.getStringCellValue();
                if (s != null && !s.isEmpty()) {
                    data.add(s);
                    cell.setCellValue("");
                    counter++;
                }
                if (counter >= necessaryAmount) break;
            }
            if (counter >= necessaryAmount) break;
        }

        inputNew.close();
        workbookNew.close();
        closeFile();
        return data;
    }

    public void closeFile() throws IOException {
        inputStream.close();
        FileOutputStream outFile = new FileOutputStream(path);
        workbook.write(outFile);
        outFile.close();
    }

    public boolean isEnoughEANAvailable(ArrayList<PhoneCover> listOfCovers) {
        int necessaryAmount = listOfCovers.size();
        Iterator<Row> iterator = firstSheet.iterator();
        int count = 0;
        System.out.println("There are " + necessaryAmount + " EANS necessary");
        System.out.println("Start counting");
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String s = cell.getStringCellValue();
                if (s != null && !s.isEmpty()) {
                    count++;
                }
            }
        }

        System.out.println("Finish counting... Counted: " + count);

        if (count >= necessaryAmount) {
            System.out.println("There are enough eans available");
            return true;
        } else {
            System.out.println("Not enough eans available");
            return false;
        }


    }


}

