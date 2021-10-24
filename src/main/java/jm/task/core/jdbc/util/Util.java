package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Connection connection;

    public Util(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost:3306/myuser";
            String USERNAME = "root";
            String PASSWORD = "1234";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Не загружен драйвер");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

   public Connection getConnection() {
       return connection;
    }

}
