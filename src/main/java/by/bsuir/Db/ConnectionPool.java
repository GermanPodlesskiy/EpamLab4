package by.bsuir.Db;

import java.sql.Connection;

/**
 * The interface Connection pool.
 */
public interface ConnectionPool {
    /**
     * Gets connection.
     *
     * @return the connection
     */
    Connection getConnection();

    /**
     * Release connection boolean.
     *
     * @param connection the connection
     * @return the boolean
     */
    boolean releaseConnection(Connection connection);
}
