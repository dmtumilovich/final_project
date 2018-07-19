package by.epam.rentacar.controller.command;

import by.epam.rentacar.dto.ChangePasswordDTO;
import by.epam.rentacar.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.util.constant.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandChangePassword implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userID = ((User)request.getSession().getAttribute(SessionAttributes.KEY_USER)).getId();
        String previousPassword = request.getParameter("edit_previous_password");
        String newPassword = request.getParameter("edit_new_password");
        String confirmPassword = request.getParameter("edit_confirm_new_password");

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setUserID(userID);
        changePasswordDTO.setPreviousPassword(previousPassword);
        changePasswordDTO.setNewPassword(newPassword);
        changePasswordDTO.setConfirmPassword(confirmPassword);

        try {
            ServiceFactory.getInstance().getUserService().changePassword(changePasswordDTO);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
