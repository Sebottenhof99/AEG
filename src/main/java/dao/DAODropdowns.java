package dao;

import data.DbConnectionSingletonFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAODropdowns {

    public ObservableList getMainMaterials() throws SQLException {
        ObservableList<String> mainMaterials  = FXCollections.observableArrayList();

        String sqlQuery="SELECT NAME "  +
                             "FROM Material ";

        Connection con = DbConnectionSingletonFactory.getConnection();

        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        while (rs.next()) {

            String name = rs.getString("name");
            mainMaterials.add(name);
        }
        return mainMaterials;
    }


    public ObservableList getAddtitionalMaterialParameters(String materialName) throws SQLException {
        ObservableList<String> additionalMaterialParameters  = FXCollections.observableArrayList();

        String getMaterialIdQuery = "SELECT name "  +
                "FROM Modell_Material " +
                "WHERE material_id = "+"(select id " +
                "from Material "+
                "where name ='"+materialName+"')";


        System.out.println(getMaterialIdQuery);

        Connection con = DbConnectionSingletonFactory.getConnection();

        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(getMaterialIdQuery);
        while (rs.next()) {

            String name = rs.getString("name");
            additionalMaterialParameters.add(name);
        }
        return additionalMaterialParameters;
    }


}