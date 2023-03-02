package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String nameUser = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/mysql";
    private  static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url,nameUser,password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
