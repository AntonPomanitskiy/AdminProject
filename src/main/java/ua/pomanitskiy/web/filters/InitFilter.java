package ua.pomanitskiy.web.filters;

import ua.pomanitskiy.classes.JdbcUserDao;
import ua.pomanitskiy.classes.User;
import ua.pomanitskiy.classes.XMLPropertiesLoaderImpl;
import ua.pomanitskiy.interfaces.UserDao;
import ua.pomanitskiy.interfaces.XMLPropertiesLoader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by anton on 06.08.16.
 *
 * @author anton
 * @version 1.1
 */
public class InitFilter implements Filter {

    /**
     * Final properties loader.
     */
    private static final XMLPropertiesLoader xmlPropertiesLoader
            = new XMLPropertiesLoaderImpl();

    /**
     * Driver string for connection to db.
     */
    public static final String DRIVER1 = xmlPropertiesLoader.getDriver();
    /**
     * Password to db.
     */
    public static final String PASSWORD1 = xmlPropertiesLoader.getPassword();
    /**
     * User who have db.
     */
    public static final String USER1 = xmlPropertiesLoader.getUsername();
    /**
     * URl for db.
     */
    public static final String URL1 = xmlPropertiesLoader.getUrl();
    /**
     * UserDao object for connection to db.
     */
    private UserDao userDao = JdbcUserDao
            .creatingUserDao(DRIVER1, URL1, USER1, PASSWORD1);
    /**
     * Request variable for http request.
     */
    private HttpServletRequest request;
    /**
     * Response variable for http response.
     */
    private HttpServletResponse response;
    /**
     * User's password.
     */
    private String password;
    /**
     * User's email.
     */
    private String email;
    /**
     * Field user.
     */
    private User user;

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

        initValues();
    }

    /**
     * Method for init values.
     */
    private void initValues() {
        user = new User();
        request.setAttribute("user", user);

        password = request.getParameter("password");
        email = request.getParameter("email");

        user.setPassword(password);
        user.setEmail(email);
        try {
            user.setFirstName(userDao.findByEmail(email).getFirstName());
            user.setLastName(userDao.findByEmail(email).getLastName());
            user.setBirthday(userDao.findByEmail(email).getBirthday());
            user.setId(userDao.findByEmail(email).getId());
            user.setRole(userDao.findByEmail(email).getRole());
            user.setBlocked(userDao.findByEmail(email).getBlocked());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void destroy() {

    }
}
