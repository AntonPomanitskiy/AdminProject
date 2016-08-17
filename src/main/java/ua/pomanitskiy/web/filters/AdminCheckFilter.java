package ua.pomanitskiy.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by anton on 06.08.16.
 */
public class AdminCheckFilter implements Filter {


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

        Principal principal = (Principal) session
                .getAttribute(Principal.PRINCIPAL);

        if (principal == null) {
            request
               .getRequestDispatcher("/WEB-INF/webPages/princNullFilter.jsp")
                    .forward(servletRequest, servletResponse);
        } else if (principal.isUserInRole(Principal.ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public final void destroy() {

    }
}
