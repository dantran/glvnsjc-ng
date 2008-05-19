package org.glvnsjc.internal.dao;

import java.util.List;

import javax.persistence.Query;

import org.glvnsjc.internal.dao.generic.GenericNameDaoJpa;
import org.glvnsjc.model.domain.Role;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Role objects.
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> 
 */
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
    public void removeRole( String rolename )
    {
        Object role = getRoleByName( rolename );
        super.entityManager.remove( role );
    }
}
