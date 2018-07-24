package by.epam.rentacar.controller.command;

import by.epam.rentacar.domain.dto.SigninDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandSignin implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter(RequestParameters.KEY_USERNAME);
        String password = request.getParameter(RequestParameters.KEY_PASSWORD);

        SigninDTO signinDTO = new SigninDTO();
        signinDTO.setUsername(username);
        signinDTO.setPassword(password);

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;

        try {
            user = userService.login(signinDTO);

            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttributes.KEY_USER, user);
                String destPage = (user.getRole() == User.Role.ADMIN) ? PageParameters.PAGE_ADMIN_PANEL : PageParameters.PAGE_MAIN;
                response.sendRedirect(request.getContextPath() + destPage);
            } else {
                request.setAttribute(RequestAttributes.KEY_INCORRECT_DATA, true);
                request.getRequestDispatcher(PageParameters.PAGE_SIGNIN).forward(request, response);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
