package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ghbdtn 129";
    private static SessionFactory sessionFactory;
    // Connect to MySQL
    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "users";
        String userName = "root";
        String password = "ghbdtn 129";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }
}
