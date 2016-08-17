package ua.pomanitskiy.classes;

import java.util.Date;

/**
 * Created by anton on 20.07.16.
 *
 * @author anton
 * @version 1.1
 */
public class User {

    /**
     * id of user.
     */
    private Long id;
    /**
     * firstName of user.
     */
    private String firstName;
    /**
     * lastName of user.
     */
    private String lastName;
    /**
     * email of user.
     */
    private String email;
    /**
     * date of birthday of user.
     */
    private Date birthday;
    /**
     * password of user.
     */
    private String password;
    /**
     * role of user.
     */
    private Role role;
    /**
     * Is user blocked or not.
     */
    private boolean blocked;

    /**
     * Public default user.
     */
    public User() {
    }

    /**
     *
     * @param id of new user
     * @param firstName of new user
     * @param lastName of new user
     * @param email of new user
     * @param password of new user
     * @param date of birthday of new user
     * @param role of new user
     * @param blocked of new user
     */
    public User(final long id,
                final String firstName,final String lastName,
                final String email, final String password,
                final Date date, final Role role,
                final boolean blocked) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = date;
        this.role = role;
        this.blocked = blocked;

    }

    /**
     *
     * @return firstName of user
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName to set first name of user
     */
    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return id of user
     */
    public final Long getId() {
        return id;
    }

    /**
     *
     * @param id to set id for user
     */
    public final void setId(final Long id) {
        this.id = id;
    }

    /**
     *
     * @return lastName of user
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName to set lastName for user
     */
    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return email of user
     */
    public final String getEmail() {
        return email;
    }

    /**
     *
     * @param email to set email for user
     */
    public final void setEmail(final String email) {
        this.email = email;
    }

    /**
     *
     * @return date of user's birthday
     */
    public final Date getBirthday() {
        return birthday;
    }

    /**
     *
     * @param blocked to set is user blocked or not
     */
    public final void setBlocked(final boolean blocked) {
        this.blocked = blocked;
    }

    /**
     *
     * @return return true if user is blocked, false if user isn't blocked
     */
    public final boolean getBlocked() {
        return blocked;
    }

    /**
     *
     * @param birthday to set birthday date of user
     */
    public final void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return role of user
     */
    public final Role getRole() {
        return role;
    }

    /**
     *
     * @param role to set role of user
     */
    public final void setRole(final Role role) {
        this.role = role;
    }

    /**
     *
     * @return password of user
     */
    public final String getPassword() {
        return password;
    }

    /**
     *
     * @param password to set password
     */
    public final void setPassword(final String password) {
        this.password = password;
    }
}
