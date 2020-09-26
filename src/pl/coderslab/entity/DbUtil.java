package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static String DB_URL = "jdbc:mysql://localhost:3306/workshop2" +
            "?useSSL=false" +
            "&characterEncoding=utf8" +
            "&serverTimezone=UTC";
    private static String DB_USER = "root";
    private static String DB_PASS = "coderslab";

    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
