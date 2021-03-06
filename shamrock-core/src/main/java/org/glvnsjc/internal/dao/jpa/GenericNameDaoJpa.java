/**
 * 
 */
package org.glvnsjc.internal.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.glvnsjc.internal.dao.GenericNameDao;
import org.glvnsjc.model.NamedEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs with unique name
 * 
 *
 */
@Transactional
public class GenericNameDaoJpa<T extends NamedEntity, PK extends Serializable>
    extends GenericDaoJpa<T, PK>
    implements GenericNameDao<T, PK>
{
    private String fieldName = "name";

    /**
     * @param persistentClass
     * @param fieldName name of the property in the POJO
     */
    public GenericNameDaoJpa( Class<T> persistentClass )
    {
        super( persistentClass );
    }

    /**
     * @param persistentClass
     */
    public GenericNameDaoJpa( Class<T> persistentClass, String fieldName )
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

    public boolean nameExists( String name )
    {
        Query q = this.entityManager.createQuery( "select o from " + persistentClass.getName() + " o where o."
            + this.fieldName + "=?" );

        q.setParameter( 1, name );

        return !q.getResultList().isEmpty() ? true : false;
    }
    
    public void removeByName( String name )
    {
        T obj = this.getByName( name );
        
        if ( obj != null )
        {
            super.remove( obj );
        }
    }

}
