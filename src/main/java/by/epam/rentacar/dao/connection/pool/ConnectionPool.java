package by.epam.rentacar.dao.connection.pool;

import by.epam.rentacar.dao.connection.DBParameter;
import by.epam.rentacar.dao.connection.DBResourceManager;

import java.sql.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class ConnectionPool {

    private static final ConnectionPool instance = new ConnectionPool();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConnectionQueue;

    private String driver;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        driver = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        url = dbResourceManager.getValue(DBParameter.DB_URL);
        user = dbResourceManager.getValue(DBParameter.DB_USER);
        password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
    }

    public void initPoolData() throws ConnectionPoolException {
        Locale.setDefault(Locale.ENGLISH);

        try {
            Class.forName(driver);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            givenAwayConnectionQueue = new ArrayBlockingQueue<>(poolSize);

            for(int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                connectionQueue.add(pooledConnection);
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't find database drive class!", e);
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in connection pool.", e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;

        try {
            connection = connectionQueue.take();
            givenAwayConnectionQueue.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source." , e);
        }

        return connection;
    }

    public void closeConnection(Connection connection, Statement st, ResultSet rs) {

        try {
            rs.close();
        } catch (SQLException e) {
            //logger
        }
        try {
            st.close();
        } catch (SQLException e) {
            //logger
        }

        try {
            connection.close();
        } catch (SQLException e) {
            //logger
        }
    }

    public void closeConnection(Connection connection, Statement st) {

        try {
            st.close();
        } catch (SQLException e) {
            //logger
        }
        try {
            connection.close();
        } catch (SQLException e) {
            //logger
        }
    }

    public void dispose() {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(givenAwayConnectionQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (SQLException e) {
            //logger;
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection = null;
        while((connection = queue.poll()) != null) {
            if(!connection.getAutoCommit()) {
                connection.commit();
            }

            ((PooledConnection) connection).reallyClose();
        }
    }


    private class PooledConnection implements Connection {

        private Connection connection = null;

        public PooledConnection(Connection connection) throws SQLException {
            this.connection = connection;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }


        @Override
        public void close() throws SQLException {
            if(connection.isClosed()) {
                throw new SQLException("Attempting to close closed connection.");
            }

            if(connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

            if(!givenAwayConnectionQueue.remove(this)) {
                throw new SQLException("Error deleting connection from the given away connections pool.");
            }

            if(!connectionQueue.offer(this)) {
                throw new SQLException("Error allocating connection in the pool.");
            }
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String s) throws SQLException {
            return connection.prepareStatement(s);
        }

        @Override
        public CallableStatement prepareCall(String s) throws SQLException {
            return connection.prepareCall(s);
        }

        @Override
        public String nativeSQL(String s) throws SQLException {
            return connection.nativeSQL(s);
        }

        @Override
        public void setAutoCommit(boolean b) throws SQLException {
            connection.setAutoCommit(b);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean b) throws SQLException {
            connection.setReadOnly(b);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String s) throws SQLException {
            connection.setCatalog(s);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int i) throws SQLException {
            connection.setTransactionIsolation(i);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int i, int i1) throws SQLException {
            return connection.createStatement(i, i1);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
            return connection.prepareStatement(s, i, i1);
        }

        @Override
        public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
            return connection.prepareCall(s, i, i1);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int i) throws SQLException {
            connection.setHoldability(i);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String s) throws SQLException {
            return connection.setSavepoint(s);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int i, int i1, int i2) throws SQLException {
            return connection.createStatement(i, i1, i2);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
            return connection.prepareCall(s, i, i1, i2);
        }

        @Override
        public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
            return connection.prepareCall(s, i, i1, i2);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i) throws SQLException {
            return connection.prepareStatement(s, i);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
            return connection.prepareStatement(s, ints);
        }

        @Override
        public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
            return connection.prepareStatement(s, strings);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int i) throws SQLException {
            return connection.isValid(i);
        }

        @Override
        public void setClientInfo(String s, String s1) throws SQLClientInfoException {
            connection.setClientInfo(s, s1);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String s) throws SQLException {
            return null;
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String s, Object[] objects) throws SQLException {
            return connection.createArrayOf(s, objects);
        }

        @Override
        public Struct createStruct(String s, Object[] objects) throws SQLException {
            return connection.createStruct(s, objects);
        }

        @Override
        public void setSchema(String s) throws SQLException {
            connection.setSchema(s);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int i) throws SQLException {
            connection.setNetworkTimeout(executor, i);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> aClass) throws SQLException {
            return connection.unwrap(aClass);
        }

        @Override
        public boolean isWrapperFor(Class<?> aClass) throws SQLException {
            return connection.isWrapperFor(aClass);
        }
    }

}
