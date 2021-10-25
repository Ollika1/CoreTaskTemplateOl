package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Connection connection;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String PASSWORD = "1234";
            String USERNAME = "root";
            String URL = "jdbc:mysql://localhost:3306/myuser";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Не загружен драйвер");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
