package ua.pomanitskiy.classes;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import ua.pomanitskiy.abstracts.AbstractJdbcDao;
import ua.pomanitskiy.interfaces.UserDao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 20.07.16.
 *
 * @author anton
 * @version 1.1
 */
public class JdbcUserDao extends AbstractJdbcDao implements UserDao {

    //Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);

    /**
     * User Dao object.
     */
    private static UserDao userDao;
    /**
     * User Dao Creating object.
     */
    private static UserDao userDaoCreating;

    /**
     * Private default constructor.
     */
    private JdbcUserDao() {
    }

    /**
     * Public constructor with params.
     *
     * @param JDBC_DRIVER driver to connect to special db
     * @param URL         url to connect to db
     * @param USER        user who have db
     * @param PASSWORD    password to connect to db
     */
    public JdbcUserDao(final String JDBC_DRIVER,
                       final String URL,
                       final String USER, final String PASSWORD) {
        super(JDBC_DRIVER, URL, USER, PASSWORD);
    }


    @Override
    public final void create(final User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"user\""
                            + " (firstname, lastname, password,"
                            + " email, birthday, role_id)"
                            + " VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(ONE, user.getFirstName());
            preparedStatement.setString(TWO, user.getLastName());
            preparedStatement.setString(THREE, user.getPassword());
            preparedStatement.setString(FOUR, user.getEmail());

            java.util.Date ubd = user.getBirthday();
            java.sql.Date bd = new Date(ubd.getTime());

            preparedStatement.setDate(FIVE, bd);
            preparedStatement.setLong(SIX, user.getRole().getId());

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
                if (connection != null
                        && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void update(final User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        createConnectionByDriverManager();
        PreparedStatement preparedStatement = null;
        //logger.error("1u");
        try {
            //      logger.error("2u");
            if (isUserExists(user)) {
                //            logger.error("3u");
                preparedStatement = connectionByDM.prepareStatement(
                        "UPDATE \"user\" SET"
                                + " firstname = ?,"
                                + " lastname = ?, password = ?,"
                                + " email = ?, birthday = ?,"
                                + " role_id = ?, blocked = ? WHERE id = ?");
                preparedStatement.setString(ONE, user.getFirstName());
                preparedStatement.setString(TWO, user.getLastName());
                preparedStatement.setString(THREE, user.getPassword());
                preparedStatement.setString(FOUR, user.getEmail());
                java.util.Date db = user.getBirthday();
                Date birthday = new Date(db.getTime());
                preparedStatement.setDate(FIVE, birthday);
                preparedStatement.setLong(SIX, user.getRole().getId());
                preparedStatement.setBoolean(SEVEN, user.getBlocked());
                preparedStatement.setLong(EIGHT, user.getId());
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null
                        && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connectionByDM != null
                        && !connectionByDM.isClosed()) {
                    connectionByDM.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void remove(final User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        createConnectionByDriverManager();
        PreparedStatement preparedStatement = null;

        try {
            if (isUserExists(user)) {
                preparedStatement = connectionByDM
                        .prepareStatement("DELETE FROM \"user\" WHERE id=?");
                preparedStatement.setLong(ONE, user.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null
                        && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connectionByDM != null
                        && !connectionByDM.isClosed()) {
                    connectionByDM.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();

        Statement statement = null;
        try {
            createConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM \"user\" ORDER BY id ASC");
            list = usersResultToList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null
                        && !statement.isClosed()) {
                    statement.close();
                }
                if (connection != null
                        && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        for (User user : list) {
            Role role = findRoleById(user.getRole().getId());
            user.setRole(role);
        }
        return list;
    }

    @Override
    public final User findByEmail(final String email) {
        createConnection();
        PreparedStatement preparedStatement = null;
        User user;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM \"user\" WHERE email=?");
            preparedStatement.setString(ONE, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = userResultToObject(resultSet);

            if (user.getEmail() != null) {
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null
                        && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null
                        && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public final boolean isUserByEmail(final String email) {

        PreparedStatement preparedStatement = null;
        try {
            createConnection();
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM \"user\" WHERE email = ?");
            preparedStatement.setString(ONE, email);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null
                        && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * @param id to find role by id.
     * @return role finded by id
     */
    private Role findRoleById(final Long id) {
        if (id < 1) {
            throw new IllegalArgumentException();
        }

        Role role = null;

        PreparedStatement preparedStatement = null;
        try {
            createConnection();
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM role WHERE id = ?");
            preparedStatement.setLong(ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException();
            }
            role = roleResultToObject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null
                        && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null
                        && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return role;
    }

    /**
     * @param resultSet to convert resultSet object to role object.
     * @return role object converted from resultSet.
     */
    private Role roleResultToObject(final ResultSet resultSet) {
        Role role = new Role();
        try {
            role.setId(resultSet.getLong("id"));
            role.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * @param resultSet to convert object to list of users.
     * @return list of users
     */
    private List<User> usersResultToList(final ResultSet resultSet) {
        List<User> listUser = new ArrayList<User>();
        try {
            while (resultSet.next()) {
                try {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));

                    user.setEmail(resultSet.getString("email"));

                    user.setFirstName(resultSet.getString("firstname"));

                    user.setLastName(resultSet.getString("lastname"));

                    user.setPassword(resultSet.getString("password"));

                    user.setRole(new Role(resultSet.getLong("role_id")));

                    user.setBirthday(resultSet.getDate("birthday"));

                    user.setBlocked(resultSet.getBoolean("blocked"));
                    listUser.add(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    /**
     * @param resultSet ot convert result set object to user object
     * @return user created by result set
     */
    private User userResultToObject(final ResultSet resultSet) {
        User user = new User();
        try {
            while (resultSet.next()) {
                try {
                    user.setId(resultSet.getLong("id"));

                    user.setEmail(resultSet.getString("email"));
                    user.setFirstName(resultSet.getString("firstname"));

                    user.setLastName(resultSet.getString("lastname"));

                    user.setPassword(resultSet.getString("password"));
                    user.setRole(new Role(resultSet.getLong("role_id")));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setBlocked(resultSet.getBoolean("blocked"));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @return user dao object
     */
    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new JdbcUserDao();
        }
        return userDao;
    }

    /**
     * @param JDBC_DRIVER driver to connect to special db
     * @param URL         url to connect to db
     * @param USER        user who have db
     * @param PASSWORD    password to connect to db
     * @return user dao object
     */
    public static UserDao creatingUserDao(final String JDBC_DRIVER,
                                          final String URL,
                                          final String USER,
                                          final String PASSWORD) {
        if (userDaoCreating == null) {
            userDaoCreating = new JdbcUserDao(JDBC_DRIVER, URL, USER, PASSWORD);
        }
        return userDaoCreating;
    }

    /**
     * @param user user to checks
     * @return true if user exists in db, false if not
     */
    private boolean isUserExists(final User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        createConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM \"user\" WHERE id = ?");
            preparedStatement.setLong(ONE, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null
                        && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null
                        && !connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * final one constant.
     */
    private final int ONE = 1;
    /**
     * final two constant.
     */
    private final int TWO = 2;
    /**
     * final three constant.
     */
    private final int THREE = 3;
    /**
     * final four constant.
     */
    private final int FOUR = 4;
    /**
     * final five constant.
     */
    private final int FIVE = 5;
    /**
     * final six constant.
     */
    private final int SIX = 6;
    /**
     * final seven constant.
     */
    private final int SEVEN = 7;
    /**
     * final eight constant.
     */
    private final int EIGHT = 8;
}
