package by.bsuir.dao;

import by.bsuir.Db.ConnectionPool;
import by.bsuir.Db.DbConnectionPool;
import by.bsuir.Db.TableDb;
import by.bsuir.bean.Table;
import by.bsuir.helper.DaoHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Table dao.
 */
public class TableDao implements by.bsuir.daoInterfaces.TableDao {
    private static ConnectionPool connectionPool;

    /**
     * Instantiates a new Table dao.
     */
    public TableDao() {
        try {
            connectionPool = DbConnectionPool.create(DaoHelper.getUrl(), DaoHelper.getUsername(), DaoHelper.getPassword());
        } catch (SQLException e) {
            connectionPool = null;
        }
    }

    @Override
    public ArrayList<Table> getTables() {
        return TableDb.getTables(connectionPool);
    }

    @Override
    public Table getTable(int id) {
        return TableDb.getTable(id, connectionPool);
    }
}
