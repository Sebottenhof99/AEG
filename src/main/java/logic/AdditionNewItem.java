package logic;

import dao.DAOAdditionNewItem;
import defines.Defines;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdditionNewItem {

    ArrayList<String> mainParameters;
    ArrayList<String> additionalParameters;
    ArrayList<String> parentSKU;

DAOAdditionNewItem daoAdditionNewItem = new DAOAdditionNewItem();

    public AdditionNewItem(ArrayList<String> mainParameters,ArrayList<String> additionalMaterials,ArrayList<String> parentSKU) {
        this.mainParameters = mainParameters;
        this.additionalParameters = additionalMaterials;
        this.parentSKU = parentSKU;
    }



    public void setItem()  {
        try {
            daoAdditionNewItem.addMaterial(mainParameters.get(Defines.addNewItem.MATERIAL));
            daoAdditionNewItem.addAdditionalMaterial(mainParameters.get(Defines.addNewItem.MATERIAL),additionalParameters);
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT1));
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT2));
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT3));
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT4));
            daoAdditionNewItem.addBulletPoint(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters.get(Defines.addNewItem.BULLET_POINT5));
            daoAdditionNewItem.addGeneral(mainParameters.get(Defines.addNewItem.MATERIAL),mainParameters);

            daoAdditionNewItem.addParentSKU(mainParameters.get(Defines.addNewItem.MATERIAL),additionalParameters,parentSKU);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Es ist nicht gelungen, neues Material anzulegen");
        }

    }


}
