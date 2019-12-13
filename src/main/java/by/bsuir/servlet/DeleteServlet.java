package by.bsuir.servlet;

import by.bsuir.dao.DaoFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The type Delete servlet.
 */
public class DeleteServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(DeleteServlet.class));

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            logger.info("Editing routine started...");
            DaoFactory.getClientDao().deleteClient(Integer.parseInt(request.getParameter("id")));
            logger.info("Edit: Successful");
            response.sendRedirect(request.getContextPath() + "/clients");
        }
        catch(Exception e) {
            logger.info(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/clients");
        }
    }
}
