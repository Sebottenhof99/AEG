package dao;

import data.DbConnectionSingletonFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOBulletpoint {

    public ArrayList<String> getBulletpoints(String materialName) throws SQLException {
        ArrayList<String> bulletpoints  = new ArrayList<>();

        String getMaterialIdQuery = "SELECT description "  +
                "FROM Bulletpoint " +
                "WHERE material_id = "+"(select id " +
                "from Material "+
                "where name ='"+materialName+"')";


        System.out.println(getMaterialIdQuery);

        Connection con = DbConnectionSingletonFactory.getConnection();

        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(getMaterialIdQuery);
        while (rs.next()) {

            String name = rs.getString("description");
            bulletpoints.add(name);
        }
        return bulletpoints;
    }


}
