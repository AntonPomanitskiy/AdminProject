package ua.pomanitskiy.web.servlets;

import ua.pomanitskiy.classes.JdbcUserDao;
import ua.pomanitskiy.interfaces.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.pomanitskiy.web.filters.InitFilter.*;

/**
 * Created by anton on 09.08.16.
 */
@WebServlet(name = "DeleteUserServlet", urlPatterns = {"/DeleteUserServlet"})
public class DeleteUserServlet extends HttpServlet {
    /**
     * UserDao object for connection to db.
     */
    private UserDao userDao = JdbcUserDao
            .creatingUserDao(DRIVER1, URL1, USER1, PASSWORD1);

    @Override
    protected final void doGet(final HttpServletRequest servletRequest,
            final HttpServletResponse servletResponse)
            throws ServletException, IOException {
        userDao.remove(userDao
                .findByEmail(servletRequest.getParameter("emailForDelete")));
        servletRequest.getRequestDispatcher("/MainServlet")
                .forward(servletRequest, servletResponse);

    }

    @Override
    protected final void doPost(final HttpServletRequest servletRequest,
                                final HttpServletResponse servletResponse)
            throws ServletException, IOException {
        doGet(servletRequest, servletResponse);
    }

}

