package ua.pomanitskiy.web.servlets;

import ua.pomanitskiy.web.action.ActionFactory;
import ua.pomanitskiy.web.action.interfaces.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by anton on 07.08.16.
 *
 * @author anton
 * @version 1.1
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
/*
    @Override
    protected final void service(final HttpServletRequest servletRequest,
                                 final HttpServletResponse servletResponse)
            throws ServletException, IOException {

        String nameAction = servletRequest.getServletPath();

        String email = (String) servletRequest.getAttribute("email");
        nameAction = "dispatcher";
        Action action = ActionFactory.getAction(nameAction);
        String goTo = action.perform(servletRequest, servletResponse);
        servletRequest.getRequestDispatcher(goTo)
                .forward(servletRequest, servletResponse);

    }
*/
    @Override
    protected final void doGet(final HttpServletRequest servletRequest,
                               final HttpServletResponse servletResponse)
            throws ServletException, IOException {

        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    protected final void doPost(final HttpServletRequest servletRequest,
                                final HttpServletResponse servletResponse)
            throws ServletException, IOException {
        doGet(servletRequest, servletResponse);
    }
}
