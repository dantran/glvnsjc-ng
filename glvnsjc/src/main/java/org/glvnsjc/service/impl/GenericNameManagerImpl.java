package org.glvnsjc.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glvnsjc.internal.dao.GenericNameDao;
import org.glvnsjc.model.NamedEntity;
import org.glvnsjc.service.GenericNameManager;
import org.glvnsjc.service.NameExistsException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * @author <a href="mailto:dantran@gmail.com">Dan Tran</a>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericNameManagerImpl<T extends NamedEntity, PK extends Serializable>
    implements GenericNameManager<T, PK>
{
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog( getClass() );

    /**
     * GenericDao instance, set by constructor of this class
     */
    protected GenericNameDao<T, PK> genericDao;

    /**
     * Public constructor for creating a new GenericManagerImpl.
     * @param genericDao the GenericDao to use for persistence
     */
    public GenericNameManagerImpl( final GenericNameDao<T, PK> genericDao )
    {
        this.genericDao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll()
    {
        return genericDao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get( PK id )
    {
        return genericDao.get( id );
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists( PK id )
    {
        return genericDao.exists( id );
    }

    /**
     * {@inheritDoc}
     */
    public T save( T object )
       throws NameExistsException
    {
        try
        {
            return genericDao.save( object );
        }
        catch ( DataIntegrityViolationException e )
        {
            log.warn( e.getMessage() );
            throw new NameExistsException( object.getName() + "' already exists!" );
        }
        catch ( EntityExistsException e )
        { // needed for JPA
            log.warn( e.getMessage() );
            throw new NameExistsException( object.getName() + "' already exists!" );
        }    
        
    }

    /**
     * {@inheritDoc}
     */
    public void remove( PK id )
    {
        genericDao.remove( id );
    }
    
    /**
     * {@inheritDoc}
     */
    public T getByName( String name )
    {
        return genericDao.getByName( name );
    }
    
}
