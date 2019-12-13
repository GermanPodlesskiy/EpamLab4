package by.bsuir.dao;

import by.bsuir.Db.ConnectionPool;
import by.bsuir.Db.DbConnectionPool;
import by.bsuir.Db.UserDb;
import by.bsuir.bean.User;
import by.bsuir.helper.DaoHelper;

import java.sql.SQLException;

/**
 * The type User dao.
 */
public class UserDao implements by.bsuir.daoInterfaces.UserDao {
    private static ConnectionPool connectionPool;

    /**
     * Instantiates a new User dao.
     */
    public UserDao() {
        try {
            connectionPool = DbConnectionPool.create(DaoHelper.getUrl(), DaoHelper.getUsername(), DaoHelper.getPassword());
        } catch (SQLException e) {
            connectionPool = null;
        }
    }

    public User getUser(String name, String password) {
        return UserDb.getUser(name, password, connectionPool);
    }

    public User getUserByName(String name) {
        return UserDb.getUserByName(name, connectionPool);
    }

    public void addUser(String name, String password) {
        UserDb.createUser(new User(name, password), connectionPool);
    }

}
