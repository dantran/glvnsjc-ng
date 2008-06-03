package org.glvnsjc.internal.dao.jpa;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.glvnsjc.internal.dao.UserDao;
import org.glvnsjc.model.domain.SchoolAdmin;
import org.glvnsjc.model.domain.SystemAdmin;
import org.glvnsjc.model.domain.User;
import org.glvnsjc.util.SpringMessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *   Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *   Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *   Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with 
 *   the new BaseDaoHibernate implementation that uses generics.
*/
@Repository("userDao")
@Transactional
public class UserDaoJpa
    extends GenericNameDaoJpa<User, Long>
    implements UserDao, UserDetailsService
{
    @Resource
    private DataSource dataSource;

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoJpa()
    {
        super( User.class );
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers()
    {
        Query q = this.entityManager.createQuery( "select u from User u order by upper(u.name)" );
        return q.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException
    {
        Query q = this.entityManager.createQuery( "select u from User u where name=?" );
        q.setParameter( 1, username );
        List<User> users = q.getResultList();
        if ( users == null || users.isEmpty() )
        {
            MessageSourceAccessor msgAccessor = SpringMessageSource.getAccessor();
            msgAccessor.getMessage( "User.usernameNotFound", username );
            throw new UsernameNotFoundException( msgAccessor.getMessage( "User.usernameNotFound", username ) );
        }
        else
        {
            return users.get( 0 );
        }
    }

    /**
     * Save user and flush entityManager
     * @param user the user to save
     * @return the updated user
     */
    @Transactional( readOnly = false )
    public User saveUser( User user )
    {
        User u = super.save( user );
        entityManager.flush();
        return u;
    }

    /**
     * {@inheritDoc}
     */
    public String getUserPassword( String username )
    {
        SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate( dataSource );
        //Table table = AnnotationUtils.findAnnotation( User.class, Table.class );
        //@fixme use hardcoded table name
        return jdbcTemplate.queryForObject( "select password from " + "user" + " where name=?", String.class,
                                            username );
    }
    
    
    @SuppressWarnings("unchecked")
    public List<SchoolAdmin> getSchoolAdmins()
    {
        return this.entityManager.createQuery("select obj from " + SchoolAdmin.class.getName() + " obj" ) .getResultList();
        
    }
    
    @SuppressWarnings("unchecked")
    public List<SystemAdmin> getSystemAdmins()
    {
        return this.entityManager.createQuery("select obj from " + SystemAdmin.class.getName() + " obj" ) .getResultList();
        
    }
    
}