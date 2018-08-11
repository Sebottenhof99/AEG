package logic;

import bdo.PhoneCover;
import defines.Defines;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainLogic {

    ExcelWriter excelWriter = new ExcelWriter();

    public void action(String inputPath, String material, String modellMaterial, String price) throws IOException {
        ArrayList<PhoneCover> list = getListOfAllPhoneCovers(inputPath);
        fillFirstLine( material, modellMaterial);
        writeAllCovers(list, material, modellMaterial, transferPrice(price));
    }

    private void fillFirstLine( String material, String modellMaterial) throws IOException {
        excelWriter.openFile(System.getProperty("user.home")+"\\Desktop\\bling.xlsx");
        final int FIRSTLINENUMBER = 3;

        String parentSKU = "PARENT SKU";
        String itemName = "ITEM NAME";
        String brandName = Defines.GeneralInformation.BRAND;
        String manufacturer = Defines.GeneralInformation.MANUFACTURER;
        String description = "DESCRIPTION";
        String product_type = Defines.GeneralInformation.PRODUCT_TYPE;
        String delete_update = Defines.GeneralInformation.UPDATE_DELETE;
        String browseNode = "BROWSE NODE";
        String GenericKeywords = "GENERIC KEYWORDS";
        String IMAGE = "FIRST IMAGE";
        String parentChild = Defines.GeneralInformation.PARENT;
        String variationTheme = "SIZE NAME OR COLOR NAME???";

        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT__SKU, FIRSTLINENUMBER),parentSKU);
    }

    private double transferPrice(String price){
        price = price.replace(",",".");
        return Double.valueOf(price);
    }


    public ArrayList getListOfAllPhoneCovers(String path) throws IOException {
        ExcelReader e = new ExcelReader();
        ArrayList<PhoneCover> phoneCovers= e.read(path);
        return phoneCovers;

    }

    public void writeAllCovers(ArrayList<PhoneCover> listOfAllPhoneCovers, String material, String modellMaterial, double price) throws IOException {
        System.out.println("Start writing files");


        for (int i = 0; i < listOfAllPhoneCovers.size(); i++) {
            int currentRow = i+4;
            PhoneCover currentItem = listOfAllPhoneCovers.get(i);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_SKU  ,currentRow), material+currentItem.getPhoneName()+currentItem.getMotive());
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.EAN  ,currentRow), "ENTER EAN");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BARCODE_TYPE  ,currentRow), Defines.GeneralInformation.BARCODE_TYPE);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_NAME  ,currentRow), "ITEM NAME");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BRAND_NAME  ,currentRow), Defines.GeneralInformation.BRAND);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.MANUFACTURER_NAME  ,currentRow), Defines.GeneralInformation.MANUFACTURER);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.DESCRIPTION  ,currentRow), "Beschreibung");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PRODUCT_TYPE  ,currentRow), Defines.GeneralInformation.PRODUCT_TYPE);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PART_NUMBER  ,currentRow), "INSERT SKU");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.MODEL  ,currentRow), "INSERT SKU");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.UPDATE_DELETE  ,currentRow), Defines.GeneralInformation.UPDATE_DELETE);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PRICE  ,currentRow), price);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.QUANTITY  ,currentRow), Defines.GeneralInformation.QUANTITY);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.CONDITION  ,currentRow), Defines.GeneralInformation.CONDITION);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.SHIPPING_GROUP  ,currentRow),"SHIPPINH GROUP");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_1  ,currentRow),"BP1");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_2  ,currentRow),"BP2");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_3  ,currentRow),"BP3");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_4  ,currentRow),"BP4");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_5  ,currentRow),"BP5");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BROWSE_NODE  ,currentRow),"Browse Node");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.SEARCH_KEYWORDS  ,currentRow),"Search Keyword");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.MAIN_IMAGE  ,currentRow),"MAIN IMAGE");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE1 ,currentRow),"IMAGE1 ");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE2 ,currentRow),"IMAGE2 ");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE3 ,currentRow),"IMAGE3 ");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE4 ,currentRow),"IMAGE4 ");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE5 ,currentRow),"IMAGE5 ");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE6 ,currentRow),"IMAGE6 ");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE7 ,currentRow),"IMAGE7 ");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.IMAGE8 ,currentRow),"IMAGE8 ");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT_CHILD ,currentRow), Defines.GeneralInformation.CHILD);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT__SKU ,currentRow), "PARENT SKU");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.RELATION_TYPE ,currentRow), Defines.GeneralInformation.RELATION_TYPE);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.VARIATION ,currentRow), "VARIATION TYPE (SIZE NAME COLOR NAME)");
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.COLOR ,currentRow), currentItem.getMotive());
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.SIZE ,currentRow), currentItem.getPhoneName());


            System.out.println("added." + i + "of " + listOfAllPhoneCovers.size());
        }
        System.out.println("stop writing files");
        excelWriter.closeFile(System.getProperty("user.home") + "\\Desktop\\bling222222.xlsx");
    }
}
