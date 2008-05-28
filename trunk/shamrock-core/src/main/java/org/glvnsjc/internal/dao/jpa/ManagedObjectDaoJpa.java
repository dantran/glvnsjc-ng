/**
 * 
 */
package org.glvnsjc.internal.dao.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.glvnsjc.internal.dao.ManagedObjectDao;
import org.glvnsjc.model.ManagedObject;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs with uniqued name
 * 
 *
 */
public class ManagedObjectDaoJpa<T extends ManagedObject, PK extends Serializable>
    extends GenericDaoJpa<T, PK>
    implements ManagedObjectDao<T, PK>
{
    private String fieldName = "name";

    /**
     * @param persistentClass
     * @param fieldName name of the property in the POJO
     */
    public ManagedObjectDaoJpa( Class<T> persistentClass )
    {
        super( persistentClass );
    }

    /**
     * @param persistentClass
     */
    public ManagedObjectDaoJpa( Class<T> persistentClass, String fieldName )
    {
        super( persistentClass );

        this.fieldName = fieldName;
    }

    /* (non-Javadoc)
     * @see com.iplocks.dao.GenericNameDao#getByName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public T getByName( String name )
    {
        Query q = this.entityManager.createQuery( "select o from " + persistentClass.getName() + " o where o."
            + this.fieldName + "=?" );

        q.setParameter( 1, name );

        List<T> objects = q.getResultList();

        if ( objects.isEmpty() )
        {
            return null;
        }

        return objects.get( 0 );
    }

    /* (non-Javadoc)
     * @see com.iplocks.dao.GenericNameDao#getByName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public T getByGuid( String guid )
    {
        Query q = this.entityManager.createQuery( "select o from " + persistentClass.getName() + " o where o.guid"
            + "=?" );

        q.setParameter( 1, guid );

        List<T> objects = q.getResultList();

        if ( objects.isEmpty() )
        {
            return null;
        }

        return objects.get( 0 );
    }

    public boolean nameExists( String name )
    {
        Query q = this.entityManager.createQuery( "select o from " + persistentClass.getName() + " o where o."
            + this.fieldName + "=?" );

        q.setParameter( 1, name );

        return !q.getResultList().isEmpty() ? true : false;
    }

    public T save( T t )
    {
        if ( t.getGuid() == null )
        {
            t.setGuid( UUID.randomUUID().toString() );
        }

        return super.save( t );
    }

    public void add( T t )
    {
        if ( t.getGuid() == null )
        {
            t.setGuid( UUID.randomUUID().toString() );
        }

        super.add( t );
    }
}
