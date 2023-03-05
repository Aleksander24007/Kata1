package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE users (\n" +
                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `lastName` varchar(45) NOT NULL,\n" +
                "  `age` int DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ")").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User("1","2",(byte)  3);
        session.save(user);
        user.setAge(age);
        user.setName(name);
        user.setLastName(lastName);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
            session.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Такого пользователя нет в базе");
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list;
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        list = session.createSQLQuery("SELECT * FROM users").getResultList();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM users").executeUpdate();
        session.getTransaction().commit();
    }
}
