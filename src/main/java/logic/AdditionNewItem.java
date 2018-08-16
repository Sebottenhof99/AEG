package logic;

import dao.DAOAdditionNewItem;
import defines.Defines;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

public class AdditionNewItem {

    ArrayList<String> mainParameters;
    TreeMap<String,String> subCategorieParantSkuPaar;


DAOAdditionNewItem daoAdditionNewItem = new DAOAdditionNewItem();

    public AdditionNewItem(ArrayList<String> mainParameters,TreeMap<String,String> additionalMaterials) {
        this.mainParameters = mainParameters;
        this.subCategorieParantSkuPaar = additionalMaterials;

    }

    public AdditionNewItem(ArrayList<String> mainParameters) {
        this.mainParameters = mainParameters;


    }



    public boolean setItem()  {
        try {
            daoAdditionNewItem.addMaterial(mainParameters.get(Defines.addNewItem.MATERIAL));
            if (subCategorieParantSkuPaar!=null){
                daoAdditionNewItem.addAdditionalMaterial(mainParameters.get(Defines.addNewItem.MATERIAL),subCategorieParantSkuPaar);
                daoAdditionNewItem.addParentSKU(mainParameters.get(Defines.addNewItem.MATERIAL),subCategorieParantSkuPaar);
            }else{

                daoAdditionNewItem.addParentSKU(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.PARENT_SKU));

            }

            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT1));
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT2));
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT3));
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT4));
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT5));
            daoAdditionNewItem.addGeneral(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters);



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Es ist nicht gelungen, neues Material anzulegen");
            return false;
        }
        return true;

    }


}
