package by.epam.rentacar.service;

import by.epam.rentacar.dto.ChangePasswordDTO;
import by.epam.rentacar.entity.User;
import by.epam.rentacar.service.exception.ServiceException;

public interface UserService {

    User login(String username, String password) throws ServiceException;
    User signup(String username, String password, String email) throws ServiceException;
    //переделать
    boolean editProfile(User user) throws ServiceException;
    void changePassword(ChangePasswordDTO changePasswordDTO) throws ServiceException;

}
