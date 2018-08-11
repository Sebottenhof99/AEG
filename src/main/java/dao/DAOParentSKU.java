package dao;

import data.DbConnectionSingletonFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOParentSKU {

    public String getParentSKU(String material, String modelMaterial ) throws SQLException {
        String parentSKU = null;


        String sqlQuery="";

        if ( modelMaterial==null || modelMaterial.isEmpty() ){

            sqlQuery="SELECT parentSKU "+
                    "FROM ParentInfo "+
                    "WHERE material_id = (select id from Material where name ='"+material+"') AND "+
                    "modell_material_id is NULL ";


        }else{
            sqlQuery="SELECT parentSKU "+
                    "FROM ParentInfo "+
                    "WHERE material_id = (select id from Material where name ='"+material+"') AND "+
                    "modell_material_id =(select id from Modell_Material where name ='"+modelMaterial+"')";


        }

        System.out.println(sqlQuery);
        System.out.println("________________");

        Connection con = DbConnectionSingletonFactory.getConnection();

        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        while (rs.next()) {

            parentSKU = rs.getString("parentSKU");

        }
        return parentSKU;
    }

}
