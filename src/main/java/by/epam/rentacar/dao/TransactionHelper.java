package by.epam.rentacar.dao;

import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHelper {

    private Connection connection;

    public TransactionHelper() throws DAOException {
        try {
            connection = ConnectionPool.getInstance().takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("error while getting connection from pool", e);
        }
    }

    public void beginTransaction(AbstractDAO dao, AbstractDAO ... daos) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            //logger
        }

        dao.setConnection(connection);
        for (AbstractDAO d : daos) {
            d.setConnection(connection);
        }
    }

    public void endTransaction() {
        try {
            connection.setAutoCommit(false);
            connection.close(); //или через пул?
        } catch (SQLException e) {
            e.printStackTrace();
            //logger
        }

    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //logger
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            //logger
        }
    }
}
