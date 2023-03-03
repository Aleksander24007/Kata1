package jm.task.core.jdbc.dao;

import com.mysql.cj.xdevapi.PreparableStatement;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Statement statement;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()){
            String createTable = "CREATE TABLE `users` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `lastName` varchar(45) NOT NULL,\n" +
                    "  `age` int DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3";
            connection.createStatement().execute("DROP TABLE IF EXISTS users");
            connection.createStatement().execute(createTable);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()){
            connection.createStatement().execute("DROP TABLE IF EXISTS users");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()){
            PreparedStatement preparableStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            preparableStatement.setString(1, name);
            preparableStatement.setString(2, lastName);
            preparableStatement.setInt(3, age);
            preparableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE Id = ?");
            statement.setInt(1,(int) id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> lst = new ArrayList<>();
        ResultSet resultSet;
        try (Connection connection = Util.getConnection()){

            resultSet = connection.createStatement().executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt(4);
                User user = new User(name, lastName, (byte) age);
                lst.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lst;

    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()){
            connection.createStatement().executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
