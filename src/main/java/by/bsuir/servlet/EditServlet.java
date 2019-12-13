package by.bsuir.servlet;

import by.bsuir.bean.Client;
import by.bsuir.bean.Table;
import by.bsuir.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The type Edit servlet.
 */
public class EditServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(EditServlet.class));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Client client = DaoFactory.getClientDao().getClient(id);

            if (client != null) {
                req.setAttribute("client", client);
                req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/clients");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/clients");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Editing routine started...");
            Client client = new Client(Integer.parseInt(req.getParameter("id")), req.getParameter("firstName"), req.getParameter("lastName"), Double.parseDouble(req.getParameter("money")));
            client.setTable(new Table(Integer.parseInt(req.getParameter("tableId")), Boolean.parseBoolean(req.getParameter("isFree")), Integer.parseInt(req.getParameter("number"))));
            DaoFactory.getClientDao().editClient(client);
            logger.info("Edit: Successful");
            resp.sendRedirect(req.getContextPath() + "/clients");
        } catch (Exception e) {
            logger.info(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
        }
    }
}
