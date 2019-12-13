package by.bsuir.daoInterfaces;

import by.bsuir.bean.Client;

import java.util.ArrayList;

/**
 * The interface Client dao.
 */
public interface ClientDao {
    /**
     * Gets client.
     *
     * @param id the id
     * @return the client
     */
    Client getClient(int id);

    /**
     * Gets clients.
     *
     * @return the clients
     */
    ArrayList<Client> getClients();

    /**
     * Delete client.
     *
     * @param id the id
     */
    void deleteClient(int id);

    /**
     * Edit client.
     *
     * @param client the client
     */
    void editClient(Client client);

    /**
     * Add client.
     *
     * @param client the client
     */
    void addClient(Client client);
}
