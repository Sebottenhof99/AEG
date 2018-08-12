package data;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionSingletonFactory {

    private static DbConnection dbConnection = new DbConnection();
    private static Connection con = null;

    public static Connection getConnection() throws SQLException {
        if(con==null || con.isClosed()){
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
