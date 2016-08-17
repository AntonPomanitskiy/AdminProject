package ua.pomanitskiy.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by anton on 08.08.16.
 * @author anton
 * @version 1.1
 */

@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected final void doGet(final HttpServletRequest servletRequest,
                               final HttpServletResponse servletResponse)
            throws ServletException, IOException {

        HttpSession session = servletRequest.getSession();
        if (session != null) {
            session.invalidate();
        }
        servletResponse.sendRedirect("index.jsp");
    }

    @Override
    protected final void doPost(final HttpServletRequest servletRequest,
                                final HttpServletResponse servletResponse)
            throws ServletException, IOException {
        doGet(servletRequest, servletResponse);
    }
}

