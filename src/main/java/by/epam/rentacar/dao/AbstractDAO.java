package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * The abstract class for all daos. Contains the basic methods to work with database.
 */
public abstract class AbstractDAO <T extends Entity> {

    protected Connection connection;

    /**
     * Adds a new object to the database table.
     *
     * @param obj the object to add.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void add(T obj) throws DAOException;

    /**
     * Updates an object in the database table.
     *
     * @param obj the object to update.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void update(T obj) throws DAOException;

    /**
     * Deletes a row from the database table depending on id.
     *
     * @param id the id to delete.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void delete(int id) throws DAOException;

    /**
     * Gets an object from the database table depending on it's id.
     *
     * @param id the object to get.
     * @return the object associated with this id.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract T getByID(int id) throws DAOException;

    /**
     * Gets the list of all objects from the database table.
     *
     * @return the list of all objects.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract List<T> getAll() throws DAOException;

    /**
     * Gets the list of objects from the database table depending on page and items per page.
     *
     * @param page is the page.
     * @param itemsPerPage is the number of items per this page.
     * @return the list of objects depending on calculated offset and limit.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract List<T> getAll(int page, int itemsPerPage) throws DAOException;

    /**
     * Gets total count of objects int the database table.
     *
     * @return the total count of all objects.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract int getTotalCount() throws DAOException;

    /**
     * Closes statement.
     *
     * @param statement is the statement to close.
     */
    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //logger
        }
    }

    /**
     * Sets connection.
     *
     * @param connection is the connection.
     */
    void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Calculates offset depending on the page and items per this page.
     *
     * @param page is the current page.
     * @param itemsPerPage is the items per this page.
     * @return the offset to get items from the database table.
     */
    protected int calculateOffset(int page, int itemsPerPage) {
        return (page - 1) * itemsPerPage;
    }

}
