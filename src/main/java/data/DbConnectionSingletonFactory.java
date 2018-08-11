package data;

import java.sql.Connection;

public class DbConnectionSingletonFactory {

    private static DbConnection dbConnection = new DbConnection();
    private static Connection con = null;

    public static Connection getConnection(){
        if(con==null){
            if(dbConnection.isDriverAvailable()){
                dbConnection.establishConnection();
                con = dbConnection.getCon();
            }
            else{
                System.out.println("Driver is not available. Returning null connection");
            }
        }
        return con;
    }
}
