package org.glvnsjc.internal.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.glvnsjc.internal.dao.RoleDao;
import org.glvnsjc.model.domain.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Role objects.
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> 
 */
@Repository("roleDao")
@Transactional
public class RoleDaoJpa
    extends GenericNameDaoJpa<Role, Long>
    implements RoleDao
{

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public RoleDaoJpa()
    {
        super( Role.class );
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Role getRoleByName( String rolename )
    {
        Query q = super.entityManager.createQuery( "select r from Role r where r.name = ?" );
        q.setParameter( 1, rolename );
        List<Role> roles = q.getResultList();

        if ( roles.isEmpty() )
        {
            return null;
        }
        else
        {
            return (Role) roles.get( 0 );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional( readOnly = false )
    public void removeRole( String rolename )
    {
        Object role = getRoleByName( rolename );
        super.entityManager.remove( role );
    }
}
