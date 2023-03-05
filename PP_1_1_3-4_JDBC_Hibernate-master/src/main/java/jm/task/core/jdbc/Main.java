package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1","Last1",(byte) 20);
        userService.saveUser("Name2","Last2",(byte) 23);
        userService.saveUser("Name3","Last3",(byte) 32);
        userService.saveUser("Name4","Last4",(byte) 43);

        userService.removeUserById(1);
        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}
