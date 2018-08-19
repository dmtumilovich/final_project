package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.dto.SigninDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.exception.ServiceException;

/**
 * The interface defines methods to work with user data.
 */
public interface UserService {

    /**
     * Authorises user. If input data is valid and user exists, returns {@link User} object.
     *
     * @param signinDTO is an {@link SigninDTO} object, containing sign in input data.
     * @return {@link User} object if data is valid and user exists and {@code null} if not.
     * @throws ServiceException
     */
    User login(SigninDTO signinDTO) throws ServiceException;

    /**
     * Registrates new user.
     *
     * @param signupDTO is an {@link SignupDTO} object, containing sign up input data.
     * @return {@link User} object if data is valid and user was successfully registered. If not returns {@code null}.
     * @throws ServiceException
     */
    User signup(SignupDTO signupDTO) throws ServiceException;

    /**
     * Gets user by {@code userID}.
     *
     * @param userID is the id for the user.
     * @return an {@link User} object if user exists or {@code null} if not.
     * @throws ServiceException
     */
    User getUser(int userID) throws ServiceException;

    /**
     * Changes user profile if input data is valid.
     *
     * @param user is a new user data.
     * @throws ServiceException
     */
    void editProfile(User user) throws ServiceException;

    /**
     * Changes password if input data is valid.
     *
     * @param changePasswordDTO is an {@link ChangePasswordDTO} object, containing changing password input data.
     * @throws ServiceException
     */
    void changePassword(ChangePasswordDTO changePasswordDTO) throws ServiceException;

    /**
     * Sets a new photo to the user according to the {@code userID}.
     *
     * @param userID is the id of the user.
     * @param filename is the photo's url.
     * @throws ServiceException
     */
    void setPhoto(int userID, String filename) throws ServiceException;

}
