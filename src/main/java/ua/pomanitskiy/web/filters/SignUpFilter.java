package ua.pomanitskiy.web.filters;

import ua.pomanitskiy.classes.JdbcRoleDao;
import ua.pomanitskiy.classes.JdbcUserDao;
import ua.pomanitskiy.classes.User;
import ua.pomanitskiy.interfaces.RoleDao;
import ua.pomanitskiy.interfaces.UserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ua.pomanitskiy.web.filters.InitFilter.*;

/**
 * Created by anton on 10.08.16.
 *
 * @author anton
 * @version 1.1
 */
public class SignUpFilter implements Filter {

    /**
     * userDao for connection with db.
     */
    private UserDao userDao = JdbcUserDao
            .creatingUserDao(DRIVER1, URL1, USER1, PASSWORD1);
    /**
     * roleDao for connection with db.
     */
    private RoleDao roleDao = JdbcRoleDao
            .creatingRoleDao(DRIVER1, URL1, USER1, PASSWORD1);
    /**
     * Request variable for http-request.
     */
    private HttpServletRequest request;
    /**
     * Response variable for http-response.
     */
    private HttpServletResponse response;
    /**
     * Field user.
     */
    private User user;
    /**
     * Field email.
     */
    private String email;
    /**
     * Field password.
     */
    private String password;


    @Override
    public final void init(final FilterConfig filterConfig)
            throws ServletException {

    }

    @Override
    public final void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        request = (HttpServletRequest) servletRequest;
        response = (HttpServletResponse) servletResponse;

        user = new User();
        request.setAttribute("user", user);

        password = request.getParameter("password");
        email = request.getParameter("email");

        user.setPassword(password);
        user.setEmail(email);
        user.setLastName(request.getParameter("lastname"));
        user.setFirstName(request.getParameter("firstname"));

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(date);
        user.setRole(roleDao.findByName("user"));

        user = userDao.findByEmail((
                (User) request.getAttribute("user")).getEmail());
        if (user == null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("indexTryAgainDuplicatedEmail.jsp");
        }


    }

    @Override
    public final void destroy() {

    }
}
