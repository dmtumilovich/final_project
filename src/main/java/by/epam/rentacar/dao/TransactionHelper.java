package by.epam.rentacar.dao;

import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHelper {

    private static final Logger logger = LogManager.getLogger(TransactionHelper.class);

    private Connection connection;

    public TransactionHelper() throws DAOException {
        try {
            connection = ConnectionPool.getInstance().takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error while getting connection from pool", e);
        }
    }

    public void beginTransaction(AbstractDAO dao, AbstractDAO ... daos) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to begin transaction!", e);
        }

        dao.setConnection(connection);
        for (AbstractDAO d : daos) {
            d.setConnection(connection);
        }
    }

    public void endTransaction() {
        try {
            connection.setAutoCommit(false);
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to end transaction!", e);
        }

    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to commit transaction", e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to rollback transaction!", e);
        }
    }

}
