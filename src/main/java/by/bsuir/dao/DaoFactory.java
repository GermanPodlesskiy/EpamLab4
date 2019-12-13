package by.bsuir.dao;

/**
 * The type Dao factory.
 */
public class DaoFactory {
    private static ClientDao clientDao = new ClientDao();
    private static UserDao userDao = new UserDao();
    private static TableDao tableDao = new TableDao();

    /**
     * Gets client dao.
     *
     * @return the client dao
     */
    public static ClientDao getClientDao() {
        return clientDao;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public static UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets table dao.
     *
     * @return the table dao
     */
    public static TableDao getTableDao() {
        return tableDao;
    }
}
