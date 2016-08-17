package ua.pomanitskiy.classes;

import ua.pomanitskiy.abstracts.AbstractJdbcDao;
import ua.pomanitskiy.interfaces.RoleDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 20.07.16.
 *
 * @author anton
 * @version 1.1
 */
public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {

    /**
     * Role dao object to connection with db.
     */
    private static RoleDao roleDao;
    /**
     * Role dao object to create connection with params to configure.
     */
    private static RoleDao roleDaoCreating;

    /**
     * Default private constructor.
     */
    private JdbcRoleDao() {

    }

    /**
     * Public constructor with params to configure connection.
     *
     * @param USER        user who have db
     * @param URL         url to connect db
     * @param PASSWORD    to connect db
     * @param JDBC_DRIVER driver to special db
     */
    public JdbcRoleDao(final String JDBC_DRIVER, final String URL,
                       final String USER, final String PASSWORD) {
        super(JDBC_DRIVER, URL, USER, PASSWORD);
    }


    @Override
    public final void create(final Role role) {
        if (role == null) {
            throw new NullPointerException();
        }
        createConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.
                    prepareStatement("INSERT INTO role(name) VALUES (?)");
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null
                        && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void update(final Role role) {
        if (role == null) {
            throw new NullPointerException();
        }
        createConnection();
        PreparedStatement preparedStatement = null;
        try {
            if (isRoleExists(role.getId())) {
                preparedStatement = connection.
                        prepareStatement("UPDATE role SET name=? WHERE id = ?");
                preparedStatement.setString(1, role.getName());
                preparedStatement.setLong(2, role.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null
                        && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void remove(final Role role) {
        if (role == null) {
            throw new NullPointerException();
        }
        createConnection();
        PreparedStatement preparedStatement = null;

        try {
            if (isRoleExists(role.getId())) {
                preparedStatement = connection.
                        prepareStatement("DELETE FROM role WHERE id = ?");
                preparedStatement.setLong(1, role.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null
                        && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final Role findByName(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        Role role = new Role();
        createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.
                    prepareStatement("SELECT * FROM role WHERE name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong("id"));
                role.setName(resultSet.getString("name"));
            }
            return role;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public final List<Role> findAll() {

        List<Role> roles = new ArrayList<Role>();

        Statement s = null;
        try {
            createConnection();
            s = connection.createStatement();
            ResultSet res = s.executeQuery("select * from role");
            while (res.next()) {
                roles.add(roleResultToObject(res));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
                if (connection != null
                        && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return roles;
    }

    /**
     * Get resultSet object and make from it the role object.
     *
     * @param currentPosition resultSet object to create role
     * @return role created by resultSet
     */
    private Role roleResultToObject(final ResultSet currentPosition) {
        Role role = new Role();

        try {
            role.setId(currentPosition.getLong("id"));
            role.setName(currentPosition.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return role;
    }

    /**
     * Get roleDao object.
     *
     * @return roleDao object
     */
    public static RoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new JdbcRoleDao();
        }
        return roleDao;
    }

    /**
     * Create roleDao object by params.
     *
     * @param USER        user who have db
     * @param URL         url to connect db
     * @param PASSWORD    to connect db
     * @param JDBC_DRIVER driver to special db
     * @return roleDao object
     */
    public static RoleDao creatingRoleDao(final String JDBC_DRIVER,
                                          final String URL,
                                          final String USER,
                                          final String PASSWORD) {
        if (roleDaoCreating == null) {
            roleDaoCreating = new JdbcRoleDao(JDBC_DRIVER, URL, USER, PASSWORD);
        }
        return new JdbcRoleDao(JDBC_DRIVER, URL, USER, PASSWORD);
    }

    /**
     * Checks whether the role is contained with the id.
     *
     * @param id to checks if exists role or not
     * @return true if role exists, flase - if don't exists
     */
    private boolean isRoleExists(final Long id) {
        createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.
                    prepareStatement("SELECT * FROM role WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
