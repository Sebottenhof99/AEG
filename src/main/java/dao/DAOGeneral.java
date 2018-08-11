package dao;

import data.DbConnectionSingletonFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

public class DAOGeneral {


    public ArrayList<String> getGeneralInformation(String materialName) throws SQLException {
        ArrayList<String> generalInformation  = new ArrayList<>();

        String getgeneralInfoIdQuery = "SELECT * "  +
                "FROM General " +
                "WHERE material_id = "+"(select id " +
                "from Material "+
                "where name ='"+materialName+"')";


        System.out.println(getgeneralInfoIdQuery);

        Connection con = DbConnectionSingletonFactory.getConnection();

        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(getgeneralInfoIdQuery);

        while (rs.next()) {


            String item_name = rs.getString("item_name");
            String product_description = rs.getString("product_description");
            String recommended_browse_nodes = rs.getString("recommended_browse_nodes");
            String generic_keywords = rs.getString("generic_keywords");
            String variation_theme = rs.getString("variation_theme");
            generalInformation.add(item_name);
            generalInformation.add(product_description);
            generalInformation.add(recommended_browse_nodes);
            generalInformation.add(generic_keywords);
            generalInformation.add(variation_theme);


        }
        return generalInformation;
    }
}
