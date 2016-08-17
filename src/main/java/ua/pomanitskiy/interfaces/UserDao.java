package ua.pomanitskiy.interfaces;

import ua.pomanitskiy.classes.User;

import java.util.List;

/**
 * Created by anton on 20.07.16.
 * @version 1.1
 * @author anton
 */
public interface UserDao {

    /**
     *   Create user.
     *   @param user to create user
     */
    void create(final User user);
    /**
     *  Update user.
     *  @param user to update user
     */
    void update(final User user);
    /**
     *   Remove user.
     *   @param user to remove user
     */
    void remove(final User user);

    /**
     *   Finding list of users.
     *   @return list of users.
     */
    List<User> findAll();
    /**
     *   Find user by email.
     *   @return user finded by email
     *   @param email to find user by email
     */
    User findByEmail(final String email);

    /**
     *   Checks whether the user this email.
     *   @return checks to consists user with this email
     *   @param email to check consist user
     */
    boolean isUserByEmail(final String email);
}
