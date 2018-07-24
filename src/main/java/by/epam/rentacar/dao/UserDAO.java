package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;

public interface UserDAO {

    User findUser(String username, String password) throws DAOException;
    User findUserByUsername(String username) throws DAOException;
    String findPasswordByUsername(String username) throws DAOException;
    String findEmail(String username) throws DAOException;
    void registerUser(SignupDTO signupDTO) throws DAOException;
    boolean signupUser(String username, String password, String email) throws DAOException;
    boolean updateUser(User user) throws DAOException;
    void changePassword(ChangePasswordDTO changePasswordDTO) throws DAOException;
    boolean checkPassword(int userID, String password) throws DAOException;

}
