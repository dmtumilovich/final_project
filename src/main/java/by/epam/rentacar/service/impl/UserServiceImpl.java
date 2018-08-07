package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.UserDAOImpl;
import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.dto.EditProfileDTO;
import by.epam.rentacar.domain.dto.SigninDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.EmailAlreadyExistsException;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.service.exception.UsernameAlreadyExistsException;
import com.google.common.hash.Hashing;
import com.sun.corba.se.spi.transport.TransportDefault;

import java.nio.charset.StandardCharsets;

public class UserServiceImpl implements UserService {

    public User login(SigninDTO signinDTO) throws ServiceException {

        User user = null;

        String hashedPassword = hashPassword(signinDTO.getPassword());
        String username = signinDTO.getUsername();

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);
            String userPassword = userDAO.findPasswordByUsername(username);

            if (userPassword == null || !userPassword.equalsIgnoreCase(hashedPassword)) {
                return null;
            }

            user = userDAO.findUserByUsername(username);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Could not login user", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return user;
    }


    public User signup(SignupDTO signupDTO) throws ServiceException {

        User user = null;

        //валидация

        String username = signupDTO.getUsername();
        String email = signupDTO.getEmail();
        String hashedPassword = hashPassword(signupDTO.getPassword());
        signupDTO.setPassword(hashedPassword);

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {

            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            if(userDAO.findUserByUsername(username) != null) {
                throw new UsernameAlreadyExistsException();
            }
//
//            if(userDAO.findEmail(username) != null) {
//                throw new EmailAlreadyExistsException();
//            }

            userDAO.registerUser(signupDTO);
            user = userDAO.findUserByUsername(username);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Could not sign up user", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return user;
    }

    @Override
    public User getUser(int userID) throws ServiceException {

        UserDAO userDAO = new UserDAOImpl();
        User user = null;
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            user = userDAO.getUserById(userID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Error while getting user data", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return user;

    }

    //переделать
    public boolean editProfile(EditProfileDTO editProfileDTO) throws ServiceException {

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {

            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            userDAO.updateUser(editProfileDTO);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Could not update user info", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return true;
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) throws ServiceException {

        String hashedPreviousPassword = hashPassword(changePasswordDTO.getPreviousPassword());
        String hashedNewPassword = hashPassword(changePasswordDTO.getNewPassword());
        String hashedConfirmPassword = hashPassword(changePasswordDTO.getConfirmPassword());

        changePasswordDTO.setPreviousPassword(hashedPreviousPassword);
        changePasswordDTO.setNewPassword(hashedNewPassword);
        changePasswordDTO.setConfirmPassword(hashedConfirmPassword);

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            if (!userDAO.checkPassword(changePasswordDTO.getUserID(), changePasswordDTO.getConfirmPassword())) {
                //exception или возвратить false
            }
            userDAO.changePassword(changePasswordDTO);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }
    }

    @Override
    public void setPhoto(int userID, String filename) throws ServiceException {

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            userDAO.setPhoto(userID, filename);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("error while updating user photo", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    private String hashPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
