package ua.pomanitskiy.web.filters;

import ua.pomanitskiy.classes.JdbcRoleDao;
import ua.pomanitskiy.classes.JdbcUserDao;
import ua.pomanitskiy.classes.User;
import ua.pomanitskiy.interfaces.RoleDao;
import ua.pomanitskiy.interfaces.UserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.pomanitskiy.web.filters.InitFilter.*;

/**
 * Created by anton on 07.08.16.
 *
 * @author anton
 * @version 1.1
 */
public class UserCheckFilter implements Filter {

    @Override
    public final void init(final FilterConfig filterConfig)
            throws ServletException {

    }

    @Override
    public final void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession();
        User user = (User) request.getAttribute("user");
        String errorDuplEmail = (String) request.getAttribute("errorDuplEmail");

        RoleDao roleDao = JdbcRoleDao
                .creatingRoleDao(DRIVER1, URL1, USER1, PASSWORD1);
        UserDao userDao = JdbcUserDao
                .creatingUserDao(DRIVER1, URL1, USER1, PASSWORD1);
        User myUser = userDao.findByEmail(user.getEmail());

        if (errorDuplEmail != null) {
            session.setAttribute(Principal.PRINCIPAL,
                    new Principal(Principal.DUPLICATE_EMAIL,
                            Principal.DUPLICATE_EMAIL));
        }
        try {
            if (myUser.getRole() != null && myUser.getPassword() != null) {
                if (myUser.getRole().getId()
                        .equals(roleDao.findByName(Principal.ADMIN).getId())
                        && myUser.getPassword().equals(user.getPassword())) {
                    session.setAttribute(Principal.PRINCIPAL,
                            new Principal(Principal.ADMIN, Principal.ADMIN));
                } else if ((myUser.getRole().getId()
                        .equals(roleDao.findByName(Principal.USER).getId()))
                        && myUser.getPassword().equals(user.getPassword())) {
                    if (myUser.getBlocked()) {
                        session.setAttribute(Principal.PRINCIPAL,
                                new Principal(Principal.BLOCKED,
                                        Principal.BLOCKED));
                    } else {
                        session.setAttribute(Principal.PRINCIPAL,
                                new Principal(Principal.USER, Principal.USER));
                    }
                }
            }
        } catch (NullPointerException e) {
            session.setAttribute(Principal.PRINCIPAL,
                    new Principal(Principal.UNREGISTER,
                            Principal.UNREGISTER));
            e.printStackTrace();
        }


        Principal principal = (Principal) session
                .getAttribute(Principal.PRINCIPAL);

        if (principal == null) {
            request.getRequestDispatcher("indexTryAgain.jsp")
                    .forward(servletRequest, servletResponse);
        } else if (principal.isUserInRole(Principal.USER)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public final void destroy() {

    }
}
