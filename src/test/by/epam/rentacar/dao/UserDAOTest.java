package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.entity.User;
import org.junit.Test;

public class UserDAOTest {

    private static final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Test(expected = NullPointerException.class)
    public void addUserWithNullParams() throws DAOException {

        String username = null;
        String email = null;
        String password = null;

        TransactionHelper transactionHelper = new TransactionHelper();
        transactionHelper.beginTransaction(userDAO);
        userDAO.add(username, email, password);
        transactionHelper.commit();
        transactionHelper.endTransaction();

    }

    @Test(expected = NullPointerException.class)
    public void updateNullUser() throws DAOException {

        User user = null;

        TransactionHelper transactionHelper = new TransactionHelper();
        transactionHelper.beginTransaction(userDAO);
        userDAO.update(user);
        transactionHelper.commit();
        transactionHelper.rollback();

    }

}
