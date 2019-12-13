package by.bsuir.Db;

import by.bsuir.bean.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The type Table db.
 */
public class TableDb {
    private static Logger logger = Logger.getLogger(String.valueOf(UserDb.class));
    private static String selectTableSql = "select * from `table` where id = ?";

    /**
     * Gets tables.
     *
     * @param connectionPool the connection pool
     * @return the tables
     */
    public static ArrayList<Table> getTables(ConnectionPool connectionPool) {
        ArrayList<Table> tables = new ArrayList<>();

        try {
            logger.info("DB selecting routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(selectTableSql.replace("?", "id"));

                while (resultSet.next()) {
                    Table table = new Table();

                    table.setId(resultSet.getInt("id"));
                    table.setNumber(resultSet.getInt("number"));
                    table.setFree(resultSet.getBoolean("is_free"));

                    tables.add(table);
                }

                resultSet.close();
                logger.info("Select: Successful");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return tables;
    }

    /**
     * Gets table.
     *
     * @param id             the id
     * @param connectionPool the connection pool
     * @return the table
     */
    public static Table getTable(int id, ConnectionPool connectionPool) {
        Table table = new Table();

        try {
            logger.info("DB selecting one routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(selectTableSql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        table.setId(resultSet.getInt("id"));
                        table.setNumber(resultSet.getInt("number"));
                        table.setFree(resultSet.getBoolean("is_free"));
                    }

                    resultSet.close();
                    logger.info("Select one: Successful");
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return table;
    }
}
