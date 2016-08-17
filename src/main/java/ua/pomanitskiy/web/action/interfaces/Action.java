package ua.pomanitskiy.web.action.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by anton on 07.08.16.
 * @version 1.1
 * @author anton
 */
public interface Action {

    /**
     * A perform method.
     *
     * @param request  object of class HttpServletRequest
     * @param response object of class HttpServletResponse
     * @return name of view for forward.
     */
    String perform(final HttpServletRequest request,
                   final HttpServletResponse response);

}


