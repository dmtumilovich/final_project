package by.epam.rentacar.controller.filter;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.SessionAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/user/*" })
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Integer userID = (Integer) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);

        if (userID != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String page = request.getHeader(RequestHeader.KEY_REFERER);
        if (page != null) {
            response.sendRedirect(page);
            return;
        }
        response.sendRedirect(PageParameters.PAGE_MAIN);
    }

    @Override
    public void destroy() {

    }
}
