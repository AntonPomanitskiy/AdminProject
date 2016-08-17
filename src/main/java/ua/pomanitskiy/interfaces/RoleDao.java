package ua.pomanitskiy.interfaces;

import ua.pomanitskiy.classes.Role;

import java.util.List;

/**
 * Created by anton on 20.07.16.
 * @version 1.1
 * @author anton
 */
public interface RoleDao {

    /**
     *   Create role.
     *   @param role object to create role
     */
    void create(final Role role);
    /**
     *   Update role.
     *   @param role object to update role
     */
    void update(final Role role);

    /**
     *   Remove role.
     *   @param role object to remove role
     */
    void remove(final Role role);

    /**
     *   Find role by name.
     *   @param name to find by name
     *   @return role by name
     */
    Role findByName(final String name);

    /**
     *   Find list of roles.
     *   @return list of roles
     */
    List<Role> findAll();
}
