package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(100), " +
                "LASTNAME VARCHAR(100), AGE INT, PRIMARY KEY (ID) )";
        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.createSQLQuery(sql).executeUpdate();

            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String sql = "DROP TABLE IF EXISTS users";
        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.createSQLQuery(sql).executeUpdate();

            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(new User(name, lastName, age));

            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            User user = (User) session.load(User.class, id);
            session.delete(user);

            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();
            users = session.createQuery("SELECT u FROM User u", User.class).
                    getResultList();
            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        List<User> users = getAllUsers();
        try (Session session = Util.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            for (User user : users) {
                removeUserById(user.getId());
            }
            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
