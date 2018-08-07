package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.dto.EditProfileDTO;
import by.epam.rentacar.domain.dto.SigninDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.exception.ServiceException;

public interface UserService {

    User login(SigninDTO signinDTO) throws ServiceException;
    User signup(SignupDTO signupDTO) throws ServiceException;
    User getUser(int userID) throws ServiceException;
    //переделать
    boolean editProfile(EditProfileDTO editProfileDTO) throws ServiceException;
    void changePassword(ChangePasswordDTO changePasswordDTO) throws ServiceException;
    void setPhoto(int userID, String filename) throws ServiceException;

}
