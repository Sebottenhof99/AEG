package logic;

import bdo.PhoneCover;
import defines.Defines;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainLogic {

    public void action(String inputPath) throws IOException {
        ArrayList<PhoneCover> list = getListOfAllPhoneCovers(inputPath);
        writeAllCovers(list);
    }


    public ArrayList getListOfAllPhoneCovers(String path) throws IOException {
        ExcelReader e = new ExcelReader();
        ArrayList<PhoneCover> phoneCovers= e.read(path);
        return phoneCovers;

    }

    public void writeAllCovers(ArrayList<PhoneCover> listOfAllPhoneCovers) throws IOException {
        System.out.println("Start writing files");

        ExcelWriter writer = new ExcelWriter();
        writer.openFile("C:\\Users\\Antoshka\\Desktop\\bling.xlsx");
        for (int i = 0; i < listOfAllPhoneCovers.size(); i++) {
            int currentRow = i+4;
            PhoneCover currentItem = listOfAllPhoneCovers.get(i);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_SKU  ,currentRow), "Test");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_NAME, currentRow), "NAME " + currentItem.getPhoneName() + " , " + currentItem.getMotive());
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.COLOR, currentRow), currentItem.getMotive());
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.SIZE, currentRow), currentItem.getPhoneName());
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.EAN, currentRow), String.valueOf(i));
            System.out.println("added." + i + "of " + listOfAllPhoneCovers.size());
        }
        System.out.println("stop writing files");
        writer.closeFile("C:\\Users\\Antoshka\\Desktop\\bling222222.xlsx");
    }
}
