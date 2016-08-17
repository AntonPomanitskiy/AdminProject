package ua.pomanitskiy.classes;

/**
 * Created by anton on 10.08.16.
 *
 * @author anton
 * @version 1.1
 */
public class Role {

    /**
     * Public default constructor.
     */
    public Role() {

    }

    /**
     * @param id of role
     */
    public Role(final long id) {
        this.id = id;
    }

    /**
     * @param id   to create role
     * @param name to create role
     */
    public Role(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * id of role.
     */
    private Long id;
    /**
     * name of role.
     */
    private String name;

    /**
     * @return name of role
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name to set name of role
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * @return id of role
     */
    public final Long getId() {
        return id;
    }

    /**
     * @param id set id of role
     */
    public final void setId(final Long id) {
        this.id = id;
    }
}
