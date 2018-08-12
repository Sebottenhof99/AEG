package data;


import defines.Defines;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static Connection con = null;




    public boolean isDriverAvailable(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver detected");
            return true;
        }
        catch (ClassNotFoundException e){
            System.out.println("No driver found");
            return false;
        }
    }

    public static Connection getCon() {
        return con;
    }

    public void establishConnection(){
        System.out.println("Connecting");

        Properties prop = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("data.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = prop.getProperty(Defines.DBProperties.USERNAME);
        String password = prop.getProperty(Defines.DBProperties.PASSWORD);
        String database = prop.getProperty(Defines.DBProperties.DATABASE);
        String url = prop.getProperty(Defines.DBProperties.URL);

        try {
            System.out.println(username);
            System.out.println(password);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Could not establish connection.");
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        DbConnection con = new DbConnection();
    }
}

