package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <T extends Entity> {

    protected Connection connection;

    public abstract void add(T obj) throws DAOException;
    public abstract void update(T obj) throws DAOException;
    public abstract void delete(int id) throws DAOException;

    public abstract T getByID(int id) throws DAOException;

    public abstract List<T> getAll() throws DAOException;
    public abstract List<T> getAll(int page, int itemsPerPage) throws DAOException;

    public abstract int getTotalCount() throws DAOException;

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //logger
            e.printStackTrace();
        }
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected int calculateOffset(int page, int itemsPerPage) {
        return (page - 1) * itemsPerPage;
    }

}
