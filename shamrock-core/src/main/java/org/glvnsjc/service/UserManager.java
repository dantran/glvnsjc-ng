package org.glvnsjc.service;

import java.util.List;

import org.glvnsjc.model.domain.SchoolAdmin;
import org.glvnsjc.model.domain.SystemAdmin;
import org.glvnsjc.model.domain.User;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a> 
 */
public interface UserManager
    extends UniversalManager
{

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    User getUser( String userId );

    /**
     * Finds a user by their username.
     * @param username the user's username used to login
     * @return User a populated user object
     * @throws org.springframework.security.userdetails.UsernameNotFoundException
     *         exception thrown when user not found
     */
    User getUserByUsername( String username )
        throws UsernameNotFoundException;

    /**
     * Retrieves a list of users, filtering with parameters on a user object
     * @param user parameters to filter on
     * @return List
     */
    List<User> getUsers();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    User saveUser( User user )
        throws NameExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser( String userId );
    
    
    /**
     * @return List of available School administrator accounts
     */
    List<SchoolAdmin> getSchoolAdmins();

    /**
     * 
     * @return List of available System administrator accounts
     */
    List<SystemAdmin> getSystemAdmins();
    
    
}
