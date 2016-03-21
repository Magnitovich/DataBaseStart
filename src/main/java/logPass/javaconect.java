package logPass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by User on 03.03.2016.
 */
public class javaconect {
    private static String URL="jdbc:mysql://localhost:3306/etude?autoReconnect=true&useSSL=true";
    private static String UserName="root";
    private static String Password="root1";
    private static Connection connection=null;

    public static Connection ConectDB() {

        try {

            connection= DriverManager.getConnection(URL, UserName, Password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; }}
    public Connection getConnection() {
        return connection;
    }
}

