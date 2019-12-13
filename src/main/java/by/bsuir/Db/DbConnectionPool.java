package by.bsuir.Db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The type Db connection pool.
 */
public class DbConnectionPool implements ConnectionPool {
    private static Logger logger = Logger.getLogger(String.valueOf(DbConnectionPool.class));

    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 3;

    /**
     * Create db connection pool.
     *
     * @param url      the url
     * @param user     the user
     * @param password the password
     * @return the db connection pool
     * @throws SQLException the sql exception
     */
    public static DbConnectionPool create(String url, String user, String password) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            logger.info(e.getMessage());
        }

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }

        return new DbConnectionPool(url, user, password, pool);
    }

    /**
     * Instantiates a new Db connection pool.
     *
     * @param url            the url
     * @param user           the user
     * @param password       the password
     * @param connectionPool the connection pool
     */
    public DbConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
