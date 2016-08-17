package ua.pomanitskiy.web.action;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import ua.pomanitskiy.classes.JdbcUserDao;
import ua.pomanitskiy.interfaces.UserDao;
import ua.pomanitskiy.web.action.interfaces.Action;
import ua.pomanitskiy.web.filters.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.pomanitskiy.web.filters.InitFilter.DRIVER1;
import static ua.pomanitskiy.web.filters.InitFilter.PASSWORD1;
import static ua.pomanitskiy.web.filters.InitFilter.URL1;
import static ua.pomanitskiy.web.filters.InitFilter.USER1;

/**
 * Created by anton on 07.08.16.
 * @version 1.1
 * @author anton
 */
public class DispatcherAction implements Action {

    /**
     * UserDao object for connection to db.
     */
    private UserDao userDao = JdbcUserDao.creatingUserDao(DRIVER1,
            URL1, USER1, PASSWORD1);

    @Override
    public final String perform(final HttpServletRequest request,
                                final HttpServletResponse response) {

        HttpSession session = request.getSession();

        Principal principal = (Principal) session
                .getAttribute(Principal.PRINCIPAL);

        System.out.println("principal from dispatcher : " + principal);

        String email = (String) request.getAttribute("email");

        if (principal == null || principal.isUserInRole(Principal.UNREGISTER)) {
            return "indexTryAgain.jsp";
        } else if (principal.isUserInRole(Principal.USER)) {
            return "/WEB-INF/webPages/userPage/userPage.jsp";
        } else if (principal.isUserInRole(Principal.BLOCKED)) {
            return "indexTryAgainBlocked.jsp";
        } else if (principal.isUserInRole(Principal.ADMIN)) {
            return "/WEB-INF/webPages/adminPage/adminPage.jsp";
        } else if (principal.isUserInRole(Principal.DUPLICATE_EMAIL)) {
            return "indexTryAgainDuplicatedEmail.jsp";
        }

        throw new RuntimeException("Don't know this role.");
    }
}
