package logic;

import bdo.PhoneCover;
import dao.DAOBulletpoint;
import dao.DAOGeneral;
import dao.DAOParentSKU;
import defines.Defines;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainLogic {

    ExcelWriter excelWriter = new ExcelWriter();

    public void action(String inputPath, String material, String modellMaterial, String price, String shippingOption) throws Exception {
        ArrayList<PhoneCover> list = getListOfAllPhoneCovers(inputPath);
        EANReader e = new EANReader(System.getProperty("user.home") + "/Desktop/EAN.xlsx");

        synchronized(EANReader.class){
            while (e.starttt(list)){

                writeAllCovers(list, material, modellMaterial, transferPrice(price), shippingOption, EANReader.EANS);
                break;
            }

        }

     /*   Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try {
                    writeAllCovers(list, material, modellMaterial, transferPrice(price), shippingOption);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        });

*/


    }


    private void fillFirstLine(String parent, ArrayList<String> generalInformation) throws IOException {
        excelWriter.openFile(System.getProperty("user.home")+"\\Desktop\\bling.xlsx");
        final int FIRSTLINENUMBER = 3;

        String parentSKU = parent;
        String itemName = generalInformation.get(Defines.GeneralInformationParser.ITEM_NAME);
        String brandName = Defines.GeneralInformation.BRAND;
        String manufacturer = Defines.GeneralInformation.MANUFACTURER;
        String description = generalInformation.get(Defines.GeneralInformationParser.DESCRIPTION);
        String product_type = Defines.GeneralInformation.PRODUCT_TYPE;
        String delete_update = Defines.GeneralInformation.UPDATE_DELETE;
        String browseNode = generalInformation.get(Defines.GeneralInformationParser.BROWSE_NODE);
        String genericKeywords = generalInformation.get(Defines.GeneralInformationParser.GENERIC_KEYWORDS);
        String image = "FIRST IMAGE";
        String parentChild = Defines.GeneralInformation.PARENT;
        String variationTheme = generalInformation.get(Defines.GeneralInformationParser.VARIATION_THEME);

        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT__SKU, FIRSTLINENUMBER),parentSKU);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_NAME, FIRSTLINENUMBER),itemName);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BRAND_NAME, FIRSTLINENUMBER),brandName);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.MANUFACTURER_NAME, FIRSTLINENUMBER),manufacturer);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.DESCRIPTION, FIRSTLINENUMBER),description);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PRODUCT_TYPE, FIRSTLINENUMBER),product_type);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.UPDATE_DELETE, FIRSTLINENUMBER),delete_update);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BROWSE_NODE, FIRSTLINENUMBER),browseNode);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.SEARCH_KEYWORDS, FIRSTLINENUMBER),genericKeywords);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.MAIN_IMAGE, FIRSTLINENUMBER),image);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT_CHILD, FIRSTLINENUMBER),parentChild);
        excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.VARIATION, FIRSTLINENUMBER),variationTheme);

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

    public void writeAllCovers(ArrayList<PhoneCover> listOfAllPhoneCovers, String material, String modellMaterial, double price, String shippingOption, ArrayList<String> eans) throws SQLException, IOException {
        System.out.println("Start writing files");
        DAOBulletpoint daoBulletpoint = new DAOBulletpoint();
        ArrayList<String> bulletPoints = daoBulletpoint.getBulletpoints(material);



        DAOParentSKU daoParentSKU = new DAOParentSKU();
        final String parentSKU = daoParentSKU.getParentSKU(material,modellMaterial);

        DAOGeneral daoGeneral = new DAOGeneral();
        ArrayList<String> generalInformation = daoGeneral.getGeneralInformation(material);

        fillFirstLine(parentSKU, generalInformation);


        for (int i = 0; i < listOfAllPhoneCovers.size(); i++) {
            int currentRow = i+4;
            PhoneCover currentItem = listOfAllPhoneCovers.get(i);
            String sku = generateSKU(material, currentItem);
            FTPImageReader ftpImageReader = new FTPImageReader();

            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_SKU  ,currentRow), sku);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.EAN  ,currentRow), eans.get(i));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BARCODE_TYPE  ,currentRow), Defines.GeneralInformation.BARCODE_TYPE);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BRAND_NAME  ,currentRow), Defines.GeneralInformation.BRAND);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.MANUFACTURER_NAME  ,currentRow), Defines.GeneralInformation.MANUFACTURER);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.DESCRIPTION  ,currentRow), generalInformation.get(Defines.GeneralInformationParser.DESCRIPTION));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PRODUCT_TYPE  ,currentRow), Defines.GeneralInformation.PRODUCT_TYPE);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PART_NUMBER  ,currentRow), sku);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.MODEL  ,currentRow), sku);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.UPDATE_DELETE  ,currentRow), Defines.GeneralInformation.UPDATE_DELETE);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PRICE  ,currentRow), price);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.QUANTITY  ,currentRow), Defines.GeneralInformation.QUANTITY);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.CONDITION  ,currentRow), Defines.GeneralInformation.CONDITION);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.SHIPPING_GROUP  ,currentRow),shippingOption);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_1  ,currentRow),bulletPoints.get(0));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_2  ,currentRow),bulletPoints.get(1));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_3  ,currentRow),bulletPoints.get(2));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_4  ,currentRow),bulletPoints.get(3));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BULLET_POINT_5  ,currentRow),bulletPoints.get(4));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.BROWSE_NODE  ,currentRow),generalInformation.get(Defines.GeneralInformationParser.BROWSE_NODE));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.SEARCH_KEYWORDS  ,currentRow),generalInformation.get(Defines.GeneralInformationParser.GENERIC_KEYWORDS));
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT_CHILD ,currentRow), Defines.GeneralInformation.CHILD);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.PARENT__SKU ,currentRow), parentSKU);
            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.RELATION_TYPE ,currentRow), Defines.GeneralInformation.RELATION_TYPE);

            String variationTheme = generalInformation.get(Defines.GeneralInformationParser.VARIATION_THEME);

            if(variationTheme.equalsIgnoreCase(Defines.VariationThemes.COLOR)){
                excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.COLOR ,currentRow), currentItem.getMotive());

            }else if(variationTheme.equalsIgnoreCase(Defines.VariationThemes.SIZE)) {
                excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.SIZE ,currentRow), currentItem.getPhoneName());
                if(material.toLowerCase().contains("glas")){
                    excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_NAME  ,currentRow), "[" + currentItem.getMotive() + "] " + generalInformation.get(Defines.GeneralInformationParser.ITEM_NAME));
                }else {
                    excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_NAME  ,currentRow), generalInformation.get(Defines.GeneralInformationParser.ITEM_NAME) + " " + currentItem.getPhoneName());
                }
            }else{
                if(material.toLowerCase().contains("glas")){
                    excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_NAME  ,currentRow), "[" + currentItem.getMotive() + "] " + generalInformation.get(Defines.GeneralInformationParser.ITEM_NAME) + " " + currentItem.getPhoneName());
                }else {
                    excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.ITEM_NAME  ,currentRow), generalInformation.get(Defines.GeneralInformationParser.ITEM_NAME) + " " + currentItem.getPhoneName() + ", " + currentItem.getMotive());
                }
                excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.SIZE ,currentRow), currentItem.getPhoneName());
                excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.COLOR ,currentRow), currentItem.getMotive());
            }

            excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.VARIATION ,currentRow), variationTheme);

            ftpImageReader.openFTPConnection();
            ArrayList<String> imagesURLs = ftpImageReader.checkImages(material, currentItem.getMotive(), currentItem.getPhoneName());
            for (int j = 0; j < imagesURLs.size(); j++) {
                excelWriter.writeXLSXFile(new Point(Defines.AmazonExcelValues.MAIN_IMAGE+j  ,currentRow), imagesURLs.get(j));
            }
            ftpImageReader.closeFTPConnection();

            System.out.println("added." + i+1 + "of " + listOfAllPhoneCovers.size());

        }
        System.out.println("FINISH");
        excelWriter.closeFile(System.getProperty("user.home") + "\\Desktop\\bling222222.xlsx");

    }

    private ArrayList<String> prepareEANs(ArrayList<PhoneCover> listOfAllPhoneCovers) {

        return null;
    }

    public String generateSKU(String material, PhoneCover cover ){
        StringBuilder sku = new StringBuilder();

        if(material.length()+cover.getMotive().length()+cover.getPhoneName().length()+2 <=40){
            sku.append(material);
            sku.append("_");
            sku.append(cover.getPhoneName());
            sku.append("_");
            sku.append(cover.getMotive());

        }else {
            sku.append(material);
            String phoneName = cover.getPhoneName();
            //removing all phone brands in front
            phoneName = phoneName.replace("Samsung Galaxy", "");
            phoneName = phoneName.replace("Apple iPhone", "");
            phoneName = phoneName.replace("LG", "");
            phoneName = phoneName.replace("Sony Xperia", "");
            phoneName = phoneName.replace("Huawei", "");
            sku.append("_");
            sku.append(phoneName);

            String motive = cover.getMotive();
            motive = motive.replace("Traumfänger", "Traumf");
            motive = motive.replace("Schwarz-Weiß" , "SW");
            motive = motive.replace("Nintedo Gameboy", "Gameboy");
            motive = motive.replace("Gameboy Nintendo", "Gameboy");
            sku.append("_");
            sku.append(motive);

        }
        return sku.toString();
    }
}
