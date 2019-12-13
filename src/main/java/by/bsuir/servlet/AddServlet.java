package by.bsuir.servlet;

import by.bsuir.bean.Client;
import by.bsuir.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The type Add servlet.
 */
public class AddServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(AddServlet.class));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tables", DaoFactory.getTableDao().getTables().stream().filter(x -> x.isFree()).toArray()  );
        req.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Adding routine started...");

            Client client = new Client(req.getParameter("firstName"), req.getParameter("lastName"), Double.parseDouble(req.getParameter("money")));

            int tableId = Integer.parseInt(req.getParameter("table"));

            if (tableId != 0) {
                client.setTable(DaoFactory.getTableDao().getTable(tableId));
                client.getTable().setFree(false);
            }

            DaoFactory.getClientDao().addClient(client);
            logger.info("Add: Successful");
            resp.sendRedirect(req.getContextPath() + "/clients");
        } catch (Exception e) {
            logger.info(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req, resp);
        }
    }
}
