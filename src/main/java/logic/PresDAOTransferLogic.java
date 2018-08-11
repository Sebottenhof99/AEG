package logic;

import dao.DAODropdowns;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class PresDAOTransferLogic {

DAODropdowns daoDropdowns = new DAODropdowns();


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
            return observableList;
        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Fehler beim Auslesen der zusätzlichen Materialparameter"); }
        return observableList;

    }


}
