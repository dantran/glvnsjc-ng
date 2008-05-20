package org.glvnsjc.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.glvnsjc.internal.dao.UserDao;
import org.glvnsjc.model.domain.User;
import org.glvnsjc.service.UserExistsException;
import org.glvnsjc.service.UserManager;
import org.glvnsjc.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@WebService(serviceName = "UserService", endpointInterface = "org.glvnsjc.service.UserService")
@Service( "userManager")
@Transactional
public class UserManagerImpl
    extends UniversalManagerImpl
    implements UserManager, UserService
{
    @Resource(name = "userDao")
    private UserDao dao;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    public User getUser( String userId )
    {
        return dao.get( new Long( userId ) );
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers( User user )
    {
        return dao.getUsers();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = false)
    public User saveUser( User user )
        throws UserExistsException
    {
        if ( user.getVersion() == null )
        {
            // if new user, lowercase userId
            user.setUsername( user.getUsername().toLowerCase() );
        }

        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if ( passwordEncoder != null )
        {
            // Check whether we have to encrypt (or re-encrypt) the password
            if ( user.getVersion() == null )
            {
                // New user, always encrypt
                passwordChanged = true;
            }
            else
            {
                // Existing user, check password in DB
                String currentPassword = dao.getUserPassword( user.getUsername() );
                if ( currentPassword == null )
                {
                    passwordChanged = true;
                }
                else
                {
                    if ( !currentPassword.equals( user.getPassword() ) )
                    {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if ( passwordChanged )
            {
                user.setPassword( passwordEncoder.encodePassword( user.getPassword(), null ) );
            }
        }
        else
        {
            log.warn( "PasswordEncoder not set, skipping password encryption..." );
        }

        try
        {
            return dao.saveUser( user );
        }
        catch ( DataIntegrityViolationException e )
        {
            e.printStackTrace();
            log.warn( e.getMessage() );
            throw new UserExistsException( "User '" + user.getUsername() + "' already exists!" );
        }
        catch ( EntityExistsException e )
        { // needed for JPA
            e.printStackTrace();
            log.warn( e.getMessage() );
            throw new UserExistsException( "User '" + user.getUsername() + "' already exists!" );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = false)
    public void removeUser( String userId )
    {
        log.debug( "removing user: " + userId );
        dao.remove( new Long( userId ) );
    }

    /**
     * {@inheritDoc}
     * @param username the login name of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when username not found
     */
    public User getUserByUsername( String username )
        throws UsernameNotFoundException
    {
        return (User) dao.loadUserByUsername( username );
    }

}
