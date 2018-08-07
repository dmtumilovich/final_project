package by.epam.rentacar.controller.filter;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute(SessionAttributes.KEY_ID_USER);
        User.Role role = (User.Role) session.getAttribute(SessionAttributes.KEY_ROLE);

        String referer = request.getHeader(RequestHeader.KEY_REFERER);
        String destPage = null;

        if (userID == null || role != User.Role.ADMIN) {
            destPage = PageParameters.PAGE_MAIN;
            response.sendRedirect(destPage);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
