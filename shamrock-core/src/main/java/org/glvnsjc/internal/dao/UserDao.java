package org.glvnsjc.internal.dao;

import java.util.List;

import org.glvnsjc.model.domain.SchoolAdmin;
import org.glvnsjc.model.domain.SystemAdmin;
import org.glvnsjc.model.domain.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UserDao
    extends GenericNameDao<User, Long>
{

    /**
     * Gets users information based on login name.
     * @param username the user's username
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.userdetails.UsernameNotFoundException thrown when user not found in database
     */
    @Transactional
    UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException;

    /**
     * Gets a list of users ordered by the uppercase version of their username.
     *
     * @return List populated list of users
     */
    List<User> getUsers();

    /**
     * Saves a user's information.
     * @param user the object to be saved
     * @return the persisted User object
     */
    User saveUser( User user );

    /**
     * Retrieves the password in DB for a user
     * @param username the user's username
     * @return the password in DB, if the user is already persisted
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    String getUserPassword( String username );
    
    List<SchoolAdmin> getSchoolAdmins();
    
    List<SystemAdmin> getSystemAdmins();
    
}
