package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.UserDAOImpl;
import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.dto.SigninDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.ServiceException;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class UserServiceImpl implements UserService {

    public User login(SigninDTO signinDTO) throws ServiceException {

        User user = null;

        String username = signinDTO.getUsername();
        String hashedPassword = hashPassword(signinDTO.getPassword());

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            user = userDAO.findUserByUsernameAndPassword(username, hashedPassword);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Could not login user", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return user;
    }


    public User signup(SignupDTO signupDTO) throws ServiceException {

        User user = null;

        String username = signupDTO.getUsername();
        String email = signupDTO.getEmail();
        String password = signupDTO.getPassword();
        String confirmPassword = signupDTO.getConfirmPassword();

        //валидация
        //если валидация прошла:
        String hashedPassword = hashPassword(password);

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {

            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            //isUsernameAlreadyExists and isEmailAlreadyExists

            userDAO.add(username, email, hashedPassword);
            user = userDAO.findUserByUsername(username);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Could not sign up user", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return user;
    }

    @Override
    public User getUser(int userID) throws ServiceException {

        User user = null;
        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            user = userDAO.getByID(userID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while getting user data", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return user;

    }

    public boolean editProfile(User user) throws ServiceException {

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {

            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            userDAO.update(user);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Could not update user info", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return true;
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) throws ServiceException {

        UserDAO userDAO = new UserDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO);

            //проверка новых паролей на совпадение
            //хеширование старого пароля и проверка на совпадение

            int userID = changePasswordDTO.getUserID();
            String hashedNewPassword = hashPassword(changePasswordDTO.getNewPassword());

            userDAO.changePassword(userID, hashedNewPassword);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while changing password!", e);
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
            throw new ServiceException("Error while updating user photo", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    private String hashPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
