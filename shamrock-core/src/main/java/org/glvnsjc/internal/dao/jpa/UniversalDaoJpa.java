package org.glvnsjc.internal.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glvnsjc.internal.dao.UniversalDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class serves as the a class that can CRUD any object witout any
 * Spring configuration. The only downside is it does require casting
 * from Object to the object class.
 *
 * @author Bryan Noll
 */
@Transactional
public class UniversalDaoJpa
    implements UniversalDao
{
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog( getClass() );

    /**
     * Entity manager, injected by Spring using @PersistenceContext annotation on setEntityManager()
     */
    protected EntityManager entityManager;

    @PersistenceContext()
    public void setEntityManager( EntityManager entityManager )
    {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    public Object save( Object o )
    {
        return this.entityManager.merge( o );
    }

    /**
     * {@inheritDoc}
     */
    @Transactional( readOnly = false )
    @SuppressWarnings("unchecked")
    public Object get( Class clazz, Serializable id )
    {
        Object o = this.entityManager.find( clazz, id );

        if ( o == null )
        {
            String msg = "Uh oh, '" + clazz + "' object with id '" + id + "' not found...";
            log.warn( msg );
            throw new EntityNotFoundException( msg );
        }

        return o;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List getAll( Class clazz )
    {
        return this.entityManager.createQuery( "select obj from " + clazz.getSimpleName() + " obj" ).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Transactional( readOnly = false )
    public void remove( Class clazz, Serializable id )
    {
        this.entityManager.remove( this.get( clazz, id ) );
    }
}
