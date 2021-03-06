package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE `myuser`.`users` (`id` BIGINT(5) NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL," +
                "`lastname` VARCHAR(45) NOT NULL, `age` TINYINT(3) NULL, PRIMARY KEY (`id`))";
        try (Connection connection = new Util().getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            System.out.println("Таблица уже существует");
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS myuser.users";
        try (Connection connection = new Util().getConnection(); Statement statement = connection.createStatement()){
            statement.execute(dropTable);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "insert into myuser.users (name, lastname, age ) values( ? ,  ? ,  ? )";
        try(Connection connection = new Util().getConnection();
                PreparedStatement statement = connection.prepareStatement(saveUser)) {
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.execute();
            System.out.println("Пользователь с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Таблица не существует");
        }
    }

    public void removeUserById(long id) {
        String deleteId = "delete from users where id=?" ;
        try (Connection connection = new Util().getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteId)){
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Пользователь с id " + id + " отсутствует");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String selectAll = "SELECT * FROM myuser.users";
        try(Connection connection = new Util().getConnection();
                PreparedStatement statement = connection.prepareStatement(selectAll)) {
            ResultSet resultSet = statement.executeQuery(selectAll);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String truncate = "TRUNCATE TABLE myuser.users";
        try (Connection connection = new Util().getConnection(); Statement statement = connection.createStatement()){
            statement.execute(truncate);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Таблица не существует");
        }
    }
}
