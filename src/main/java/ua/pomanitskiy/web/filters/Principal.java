package ua.pomanitskiy.web.filters;

/**
 * Created by anton on 06.08.16.
 *
 * @author anton
 * @version 1.1
 */
public final class Principal {

    /**
     *
     */
    public static final String PRINCIPAL = "principal";
    /**
     *
     */
    public static final String USER = "user";
    /**
     *
     */
    public static final String ADMIN = "admin";
    /**
     *
     */
    public static final String UNREGISTER = "unregister";
    /**
     *
     */
    public static final String BLOCKED = "blocked";
    /**
     *
     */
    public static final String DUPLICATE_EMAIL = "duplicate_email";

    /**
     *
     */
    private String name;
    /**
     *
     */
    private String role;

    /**
     *
     * @param name for set name
     * @param role for set role
     */
    public Principal(final String name, final String role) {
        this.name = name;
        this.role = role;
    }

    /**
     * @return name of principal
     */
    public String getName() {
        return name;
    }

    /**
     * @return role of principal
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role for check equals of roles.
     * @return true if roles equals, or false if roles are not equals.
     */
    public boolean isUserInRole(final String role) {
        return this.role.equals(role);
    }

    @Override
    public String toString() {
        return "Principal [name=" + name + ", role=" + role + "]";
    }


}
