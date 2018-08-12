package dao;

import data.DbConnectionSingletonFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOShipping {

    public ObservableList<String> getShippingOption() throws SQLException {
        ObservableList<String> shippingOptiions = FXCollections.observableArrayList();

        String sqlQuery = "SELECT option "+
                "FROM ShippingOption ";


        Connection con = DbConnectionSingletonFactory.getConnection();

        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        while (rs.next()){
            String option = rs.getString("option");
            shippingOptiions.add(option);
        }
        con.close();
        return shippingOptiions;


    }



}
