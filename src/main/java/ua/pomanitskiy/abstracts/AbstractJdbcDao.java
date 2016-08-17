package ua.pomanitskiy.abstracts;

import org.apache.commons.dbcp.DriverConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * AbstractJdbcDao class. Use to create db connection.
 *
 * @author anton
 * @version 1.1
 */
public abstract class AbstractJdbcDao {

    /**
     *   Jdbc driver.
     */
    private String jdbc_driver;
    /**
     * Url for db.
     */
    private String url;
    /**
     *   User for db.
     */
    private String user;
    /**
     *   Password for user.
     */
    private String password;
    /**
     *   DataSource variable.
     */
    private static DataSource dataSource = null;
    /**
     *   Connection variable for get connection from pool be dataSource.
     */
    protected Connection connection;
    /**
     *   Connection variable for get connection from DriverManager.
     */
    protected Connection connectionByDM;

    /**
     *   Default constructor.
     */
    protected AbstractJdbcDao() {

    }

    /**
     *   Public constructor with parameters.
     *   @param JDBC_DRIVER driver to db connectio
     *   @param PASSWORD password to db
     *   @param URL url to connect db
     *   @param USER user who have this db
     */
    public AbstractJdbcDao(final String JDBC_DRIVER, final String URL,
                           final String USER, final String PASSWORD) {

        this.jdbc_driver = JDBC_DRIVER;
        this.url = URL;
        this.user = USER;
        this.password = PASSWORD;

    }

    /**
     *  Create connection by driver manager.
     *  @return connection created by driver manager
     */
    protected final Connection createConnectionByDriverManager() {
        try {
            Class.forName(jdbc_driver);
            connectionByDM = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connectionByDM;
    }

    /**
     *   Create connection by data source.
     *   @return connection created by dataSource
     */
    protected final Connection createConnection() {
        try {
            if (dataSource == null) {
                dataSource = getDataSource();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return connection;
    }

    /**
     *   Create data source object from pool.
     *   @return data source object
     */
    private DataSource getDataSource() {

        Driver driver = null;
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        try {
            driver = (Driver) Class.forName(jdbc_driver).newInstance();
        } catch (IllegalAccessException | ClassNotFoundException
                                        | InstantiationException e) {
            e.printStackTrace();
        }
        PoolableConnectionFactory factory = new PoolableConnectionFactory(
                new DriverConnectionFactory(driver, url, properties),
                new GenericObjectPool(null), null, "SELECT 1", false, true);
        return new PoolingDataSource(factory.getPool());
    }

}

