package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.dto.EditProfileDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;

public abstract class UserDAO extends AbstractDAO{

    public abstract User getUserById(int userID) throws DAOException;
    public abstract User findUser(String username, String password) throws DAOException;
    public abstract User findUserByUsername(String username) throws DAOException;
    public abstract int findUserIdByUsername(String username) throws DAOException;
    public abstract String findPasswordByUsername(String username) throws DAOException;
    public abstract String findEmail(String username) throws DAOException;
    public abstract void registerUser(SignupDTO signupDTO) throws DAOException;
    public abstract boolean signupUser(String username, String password, String email) throws DAOException;
    public abstract boolean updateUser(EditProfileDTO editProfileDTO) throws DAOException;
    public abstract void changePassword(ChangePasswordDTO changePasswordDTO) throws DAOException;
    public abstract boolean checkPassword(int userID, String password) throws DAOException;

}
