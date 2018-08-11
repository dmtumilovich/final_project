package by.epam.rentacar.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {

    protected Connection connection;

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
