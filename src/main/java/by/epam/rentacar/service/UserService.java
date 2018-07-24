package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.dto.SigninDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.exception.ServiceException;

public interface UserService {

    User login(SigninDTO signinDTO) throws ServiceException;
    User signup(SignupDTO signupDTO) throws ServiceException;
    //переделать
    boolean editProfile(User user) throws ServiceException;
    void changePassword(ChangePasswordDTO changePasswordDTO) throws ServiceException;

}
