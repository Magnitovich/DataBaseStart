package logPass.aDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by User on 03.03.2016.
 */
public class javaconectDB {
    private static String URL="jdbc:mysql://localhost:3306/etude?autoReconnect=true&useSSL=true";
    private static String UserName="root";
    private static String Password="root1";
    private static Connection connection;

    public static Connection ConectDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = null;
            connection = DriverManager.getConnection(URL, UserName, Password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

