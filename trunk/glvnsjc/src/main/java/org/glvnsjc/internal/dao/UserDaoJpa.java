package org.glvnsjc.internal.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.glvnsjc.internal.dao.generic.GenericNameDaoJpa;
import org.glvnsjc.model.domain.User;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
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
public class UserDaoJpa
    extends GenericNameDaoJpa<User, Long>
    implements UserDao, UserDetailsService
{
    private DataSource dataSource;

    /**
     * Constructor that sets the entity to User.class.
     * @param dataSource the dataSource to use for looking up user's password with a jdbcTemplate
     */
    public UserDaoJpa( DataSource dataSource )
    {
        super( User.class );
        this.dataSource = dataSource;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers()
    {
        Query q = this.entityManager.createQuery( "select u from User u order by upper(u.username)" );
        return q.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException
    {
        Query q = this.entityManager.createQuery( "select u from User u where username=?" );
        q.setParameter( 1, username );
        List<User> users = q.getResultList();
        if ( users == null || users.isEmpty() )
        {
            throw new UsernameNotFoundException( "user '" + username + "' not found..." );
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
        Table table = AnnotationUtils.findAnnotation( User.class, Table.class );
        return jdbcTemplate.queryForObject( "select password from " + table.name() + " where username=?", String.class,
                                            username );
    }
}