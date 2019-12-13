package by.bsuir.servlet;

import by.bsuir.bean.Client;
import by.bsuir.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Client servlet.
 */
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Client> clients = DaoFactory.getClientDao().getClients();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/WEB-INF/views/client.jsp").forward(req, resp);
    }
}
