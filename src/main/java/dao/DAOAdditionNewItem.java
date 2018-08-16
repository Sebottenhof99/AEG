package dao;

import data.DbConnectionSingletonFactory;
import defines.Defines;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DAOAdditionNewItem {

    public void addMaterial(String material) throws SQLException {
        System.out.println("Add new material: "+material);

        Connection con = DbConnectionSingletonFactory.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Material (name) VALUE (?)");
        preparedStatement.setString(1, material);

        preparedStatement.execute();
        preparedStatement.close();

    }


    private Integer getIdByMaterial(String material) throws SQLException {

        System.out.println("Get Id by Material");
      String  sqlQuery="SELECT id "+
                "FROM Material "+
                "WHERE name ='"+material+"'";

        Integer id=null;
        Connection con = DbConnectionSingletonFactory.getConnection();
        System.out.println("con:"+ con.isClosed());

        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        while (rs.next()) {
            id  = rs.getInt("id");
        }
        return id;

    }

    private Integer getIdByModellMaterial(String modellMaterial) throws SQLException {

        System.out.println("Get Id by ModellMaterial");
        String  sqlQuery="SELECT id "+
                "FROM Modell_Material "+
                "WHERE name = '" + modellMaterial+"'";

        Integer id=null;
        Connection con = DbConnectionSingletonFactory.getConnection();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        while (rs.next()) {
            id  = rs.getInt("id");
        }
        return id;


    }

    public void addAdditionalMaterial(String material, TreeMap<String,String> modelMaterial) throws SQLException {
        Integer materialId = getIdByMaterial(material);
        System.out.println("Add new addtitonal material for : "+material+"with id " + materialId);

        Connection con = DbConnectionSingletonFactory.getConnection();

        for (Map.Entry<String, String> entry : modelMaterial.entrySet()) {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Modell_Material(material_id, name) VALUES (?,?)");
            preparedStatement.setInt(1, materialId);
            preparedStatement.setString(2,entry.getKey());
            preparedStatement.execute();
            preparedStatement.close();
        }
    }


    public void addBulletPoint(String material, String bulletPoint) throws SQLException {

        Integer materialId = getIdByMaterial(material);
        System.out.println("Add bullet point  material for : "+material+"with id " + materialId);


        Connection con = DbConnectionSingletonFactory.getConnection();


            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Bulletpoint(material_id, description) VALUES (?,?)");
            preparedStatement.setInt(1, materialId);
            preparedStatement.setString(2, bulletPoint);

            preparedStatement.execute();
            preparedStatement.close();


    }

    public void addGeneral(String material, ArrayList<String> general) throws SQLException {

        Integer materialId = getIdByMaterial(material);
        System.out.println("Add general information   for : "+material+" with id " + materialId);


        Connection con = DbConnectionSingletonFactory.getConnection();


        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO General(material_id, item_name,product_description,recommended_browse_nodes,generic_keywords, variation_theme) VALUES (?,?,?,?,?,?)");
        preparedStatement.setInt(1, materialId);
        preparedStatement.setString(2, general.get(Defines.addNewItem.ARTIKELNAME));
        preparedStatement.setString(3, general.get(Defines.addNewItem.BESCHEIBUNG));
        preparedStatement.setString(4, general.get(Defines.addNewItem.SUCHKATEGORIE));
        preparedStatement.setString(5, general.get(Defines.addNewItem.SUCHWOERTER));
        preparedStatement.setString(6, general.get(Defines.addNewItem.VARIATION));

        preparedStatement.execute();
        preparedStatement.close();


    }

    public void addParentSKU(String material, TreeMap<String, String> subCategorieParentSku) throws SQLException {

        Integer materialId = getIdByMaterial(material);
        System.out.println("Add general information   for : " + material + " with id " + materialId);


        Connection con = DbConnectionSingletonFactory.getConnection();

        for (Map.Entry<String, String> entry : subCategorieParentSku.entrySet())
        {
            Integer modellMaterialId = getIdByModellMaterial(entry.getKey());

            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO ParentInfo(material_id, modell_material_id,parentSKU) VALUES (?,?,?)");
            preparedStatement.setInt(1, materialId);
            preparedStatement.setInt(2, modellMaterialId);
            preparedStatement.setString(3, entry.getValue());
            preparedStatement.execute();
            preparedStatement.close();
        }

    }



    public void addParentSKU(String material, String parentSku) throws SQLException {

        Integer materialId = getIdByMaterial(material);
        System.out.println("Add parent SKU information   for : " + material + " with id " + materialId);

        Connection con = DbConnectionSingletonFactory.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO ParentInfo(material_id, modell_material_id,parentSKU) VALUES (?,?,?)");
        preparedStatement.setInt(1, materialId);
        preparedStatement.setNull(2, java.sql.Types.INTEGER);
        preparedStatement.setString(3,parentSku);
        preparedStatement.execute();
        preparedStatement.close();
    }
}