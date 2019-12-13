package by.bsuir.Db;

import by.bsuir.bean.Client;
import by.bsuir.bean.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The type Client db.
 */
public class ClientDb {
    private static Logger logger = Logger.getLogger(String.valueOf(ClientDb.class));
    private static String selectClients = "select c.id, c.first_name, c.last_name, c.money, t.id as 'table_id', t.`number`, t.is_free from `client` c\n" +
            "left join `table` t on c.table_id = t.id\n" +
            "where c.id = ?";
    private static final String insertClient = "insert into `client` (`first_name`, `last_name`, `money`, `table_id`) values (?, ?, ?, ?)";
    private static final String updateClient = "update `client` set first_name = ?, last_name = ?, money = ?, table_id = ? where id = ?";
    private static final String deleteClient = "delete from client where id = ?";
    private static final String selectTableId = "select t.id from `table` t";
    private static final String insertTable = "insert into `table` (`id`, `is_free`, `number`) values";


    /**
     * Gets clients.
     *
     * @param connectionPool the connection pool
     * @return the clients
     */
    public static ArrayList<Client> getClients(ConnectionPool connectionPool) {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            logger.info("DB selecting routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(selectClients.replace("?", "c.id"));

                while (resultSet.next()) {
                    Client client = new Client();

                    client.setId(resultSet.getInt("id"));
                    client.setFirstName(resultSet.getString("first_name"));
                    client.setLastName(resultSet.getString("last_name"));
                    client.setMoney(resultSet.getDouble("money"));

                    try {
                        client.setTable(new Table());
                        client.getTable().setId(resultSet.getInt("table_id"));
                        client.getTable().setNumber(resultSet.getInt("number"));
                        client.getTable().setFree(resultSet.getBoolean("is_free"));
                    } catch (Exception e) {
                        client.setTable(null);
                    }

                    clients.add(client);
                }

                resultSet.close();
                logger.info("Select: Successful");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return clients;
    }

    /**
     * Gets client.
     *
     * @param id             the id
     * @param connectionPool the connection pool
     * @return the client
     */
    public static Client getClient(int id, ConnectionPool connectionPool) {
        Client client = new Client();

        try {
            logger.info("DB selecting one routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(selectClients)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        client.setId(resultSet.getInt("id"));
                        client.setFirstName(resultSet.getString("first_name"));
                        client.setLastName(resultSet.getString("last_name"));
                        client.setMoney(resultSet.getDouble("money"));
                        client.setTable(new Table());
                        client.getTable().setId(resultSet.getInt("table_id"));
                        client.getTable().setNumber(resultSet.getInt("number"));
                        client.getTable().setFree(resultSet.getBoolean("is_free"));
                    }

                    resultSet.close();
                    logger.info("Select one: Successful");
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return client;
    }

    /**
     * Create client int.
     *
     * @param client         the client
     * @param connectionPool the connection pool
     * @return the int
     */
    public static int createClient(Client client, ConnectionPool connectionPool) {
        try {
            logger.info("DB inserting routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                Statement statement = conn.createStatement();

                if (client.getTable() != null && getTableIds(statement).stream().noneMatch(x -> x != client.getId())) {
                    statement.executeUpdate(insertTable + "(" + client.getTable().getId() + "," + client.getTable().isFree() + "," + client.getTable().getNumber() + ")");
                }

                try (PreparedStatement preparedStatement = conn.prepareStatement(insertClient)) {
                    preparedStatement.setString(1, client.getFirstName());
                    preparedStatement.setString(2, client.getLastName());
                    preparedStatement.setDouble(3, client.getMoney());

                    if (client.getTable() == null) {
                        preparedStatement.setNull(4, Types.INTEGER);
                    } else {
                        preparedStatement.setInt(4, client.getTable().getId());
                    }

                    logger.info("Insert: Successful");

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return 0;
    }

    /**
     * Update client int.
     *
     * @param client         the client
     * @param connectionPool the connection pool
     * @return the int
     */
    public static int updateClient(Client client, ConnectionPool connectionPool) {
        try {
            logger.info("DB updating routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(updateClient)) {
                    preparedStatement.setString(1, client.getFirstName());
                    preparedStatement.setString(2, client.getLastName());
                    preparedStatement.setDouble(3, client.getMoney());

                    if (client.getTable() == null) {
                        preparedStatement.setNull(4, Types.INTEGER);
                    } else {
                        preparedStatement.setInt(4, client.getTable().getId());
                    }

                    preparedStatement.setInt(5, client.getId());
                    logger.info("Update: Successful");

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return 0;
    }

    /**
     * Delete int.
     *
     * @param id             the id
     * @param connectionPool the connection pool
     * @return the int
     */
    public static int delete(int id, ConnectionPool connectionPool) {
        try {
            logger.info("DB deleting routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(deleteClient)) {
                    preparedStatement.setInt(1, id);
                    logger.info("Delete: Successful");

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return 0;
    }

    private static List<Integer> getTableIds(Statement statement) throws SQLException {
        ResultSet resultSet = null;
        resultSet = statement.executeQuery(selectTableId);
        ArrayList<Integer> ids = new ArrayList<>();

        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }

        resultSet.close();

        return ids;
    }
}
