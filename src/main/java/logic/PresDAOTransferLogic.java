package logic;

import dao.DAOBulletpoint;
import dao.DAODropdowns;
import dao.DAOGeneral;
import dao.DAOParentSKU;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class PresDAOTransferLogic {

DAODropdowns daoDropdowns = new DAODropdowns();
DAOBulletpoint daoBulletpoint = new DAOBulletpoint();
DAOGeneral daoGeneral= new DAOGeneral();
DAOParentSKU daoParentSKU = new DAOParentSKU();


    public ObservableList<String> getMaterials(){
        ObservableList<String>  observableList = null;
        try {
          observableList   = daoDropdowns.getMainMaterials();
            return observableList;
        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Fehler beim Abholen der Materialbeschreibung"); }
            return observableList;

    }

    public ObservableList<String> getAdditionalMaterialParameters(String materialName){
        ObservableList<String>  observableList = null;
        try {
            observableList   = daoDropdowns.getAddtitionalMaterialParameters(materialName);

        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Fehler beim Auslesen der zusätzlichen Materialparameter"); }
        return observableList;

    }

    public ArrayList<String> getBulletpoints(String materialName){
        ArrayList<String> bulletpoints = null;
        try {
            bulletpoints   = daoBulletpoint.getBulletpoints(materialName);

        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Fehler beim Auslesen der bulletpoints"); }
        return bulletpoints;
    }


    public ArrayList<String> getGeneralInformation(String materialName){
        ArrayList<String> generalInformation = null;
        try {
            generalInformation   = daoGeneral.getGeneralInformation(materialName);
            }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Fehler beim Auslesen der allgemeinen Information"); }
        return generalInformation;
    }


    public String getParentSKU(String materialName, String modellMaterial){
       String parentSKU = "";
        try {
            parentSKU   = daoParentSKU.getParentSKU(materialName, modellMaterial);
        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Fehler beim Auslesen des ParentSKU-Parameters"); }
        return parentSKU;
    }


}
