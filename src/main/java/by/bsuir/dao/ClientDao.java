package by.bsuir.dao;

import by.bsuir.Db.ClientDb;
import by.bsuir.Db.ConnectionPool;
import by.bsuir.Db.DbConnectionPool;
import by.bsuir.bean.Client;
import by.bsuir.helper.DaoHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Client dao.
 */
public class ClientDao implements by.bsuir.daoInterfaces.ClientDao {
    private static ConnectionPool connectionPool;

    /**
     * Instantiates a new Client dao.
     */
    public ClientDao() {
        try {
            connectionPool = DbConnectionPool.create(DaoHelper.getUrl(), DaoHelper.getUsername(), DaoHelper.getPassword());
        } catch (SQLException e) {
            connectionPool = null;
        }
    }

    @Override
    public Client getClient(int id) {
        return ClientDb.getClient(id, connectionPool);
    }

    @Override
    public ArrayList<Client> getClients() {
        return ClientDb.getClients(connectionPool);
    }

    @Override
    public void deleteClient(int id) {
        ClientDb.delete(id, connectionPool);
    }

    @Override
    public void editClient(Client client) {
        ClientDb.updateClient(client, connectionPool);
    }

    @Override
    public void addClient(Client client) {
        ClientDb.createClient(client, connectionPool);
    }
}
