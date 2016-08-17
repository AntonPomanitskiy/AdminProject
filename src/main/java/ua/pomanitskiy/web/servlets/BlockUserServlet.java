package ua.pomanitskiy.web.servlets;

import ua.pomanitskiy.classes.JdbcUserDao;
import ua.pomanitskiy.classes.User;
import ua.pomanitskiy.interfaces.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.pomanitskiy.web.filters.InitFilter.*;

/**
 * Created by anton on 10.08.16.
 * @version 1.1
 * @author anton
 */
@WebServlet(name = "BlockUserServlet", urlPatterns = {"/BlockUserServlet"})
public class BlockUserServlet extends HttpServlet {

    /**
     * UserDao for connection ot db.
     */
    private UserDao userDao
            = JdbcUserDao.creatingUserDao(DRIVER1, URL1, USER1, PASSWORD1);

    @Override
    protected final void doGet(final HttpServletRequest servletRequest,
                               final HttpServletResponse servletResponse)
            throws ServletException, IOException {

        User user = userDao
                .findByEmail(servletRequest.getParameter("emailForBlock"));
        user.setBlocked(!user.getBlocked());
        userDao.update(user);
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

