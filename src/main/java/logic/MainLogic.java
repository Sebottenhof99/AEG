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
        writer.openFile(System.getProperty("user.home")+"\\Desktop\\bling.xlsx");
        for (int i = 0; i < listOfAllPhoneCovers.size(); i++) {
            int currentRow = i+4;
            PhoneCover currentItem = listOfAllPhoneCovers.get(i);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_SKU  ,currentRow), "INSERT SKU");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.EAN  ,currentRow), "ENTER EAN");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.BARCODE_TYPE  ,currentRow), Defines.GeneralInformation.BARCODE_TYPE);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_NAME  ,currentRow), "ITEM NAME");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.BRAND_NAME  ,currentRow), Defines.GeneralInformation.BRAND);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.MANUFACTURER_NAME  ,currentRow), Defines.GeneralInformation.MANUFACTURER);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.DESCRIPTION  ,currentRow), "Beschreibung");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.PRODUCT_TYPE  ,currentRow), Defines.GeneralInformation.PRODUCT_TYPE);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.PART_NUMBER  ,currentRow), "INSERT SKU");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.MODEL  ,currentRow), "INSERT SKU");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.UPDATE_DELETE  ,currentRow), Defines.GeneralInformation.UPDATE_DELETE);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.PRICE  ,currentRow), "PREIS");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.QUANTITY  ,currentRow), Defines.GeneralInformation.QUANTITY);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.CONDITION  ,currentRow), Defines.GeneralInformation.CONDITION);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.SHIPPING_GROUP  ,currentRow),"SHIPPINH GROUP");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_1  ,currentRow),"BP1");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_2  ,currentRow),"BP2");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_3  ,currentRow),"BP3");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_4  ,currentRow),"BP4");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_5  ,currentRow),"BP5");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.BROWSE_NODE  ,currentRow),"Browse Node");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.SEARCH_KEYWORDS  ,currentRow),"Search Keyword");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.MAIN_IMAGE  ,currentRow),"MAIN IMAGE");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE1 ,currentRow),"IMAGE1 ");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE2 ,currentRow),"IMAGE2 ");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE3 ,currentRow),"IMAGE3 ");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE4 ,currentRow),"IMAGE4 ");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE5 ,currentRow),"IMAGE5 ");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE6 ,currentRow),"IMAGE6 ");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE7 ,currentRow),"IMAGE7 ");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE8 ,currentRow),"IMAGE8 ");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT_CHILD ,currentRow), Defines.GeneralInformation.CHILD);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT__SKU ,currentRow), "PARENT SKU");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.RELATION_TYPE ,currentRow), Defines.GeneralInformation.RELATION_TYPE);
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.VARIATION ,currentRow), "VARIATION TYPE (SIZE NAME COLOR NAME)");
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.COLOR ,currentRow), currentItem.getMotive());
            writer.writeXLSXFile(new Point(Defines.AmazonExcelValues.SIZE ,currentRow), currentItem.getPhoneName());


            System.out.println("added." + i + "of " + listOfAllPhoneCovers.size());
        }
        System.out.println("stop writing files");
        writer.closeFile(System.getProperty("user.home") + "\\Desktop\\bling222222.xlsx");
    }
}
