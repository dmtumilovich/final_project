package by.epam.rentacar.controller;

import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The class to track servlet context lifecycle.
 * Instantiates and initializes connection pool when context initialized and
 * disposes pool when context destroyed.
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
            //logger
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().dispose();
    }
}
