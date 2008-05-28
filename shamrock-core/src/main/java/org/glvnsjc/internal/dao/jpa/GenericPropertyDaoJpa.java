/**
 * 
 */
package org.glvnsjc.internal.dao.jpa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.glvnsjc.internal.dao.GenericPropertyDao;
import org.glvnsjc.model.PropertyEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic DAO (Data Access Object) 
 *
 */
@Transactional
public class GenericPropertyDaoJpa<T extends PropertyEntity, PK extends Serializable>
    extends GenericNameDaoJpa<T, PK>
    implements GenericPropertyDao<T, PK>
{
    /**
     * @param persistentClass
     * @param fieldName name of the property in the POJO
     */
    public GenericPropertyDaoJpa( Class<T> persistentClass )
    {
        super( persistentClass );
    }

    /**
     * @param persistentClass
     */
    public GenericPropertyDaoJpa( Class<T> persistentClass, String fieldName )
    {
        super( persistentClass, fieldName );
    }

    public T getByName( String name, Class<T> clazz )
    {
        T obj = super.getByName( name );

        if ( obj == null )
        {
            try
            {
                obj = clazz.newInstance();
                obj.setName( name );
            }
            catch ( Exception e )
            {
                throw new RuntimeException( e );
            }
        }
        else
        {
            obj.fileOldValue();
        }

        return obj;
    }

    public T getById( PK id )
    {
        T obj = super.get( id );
        
        if ( obj != null )
        {
            obj.fileOldValue();
        }
        
        return obj;
    }
    
    @Transactional( readOnly = false )
    public T save( T obj )
    {
        if ( obj.isDirty() )
        {
            obj = super.save( obj );
        }
        
        return obj;
    }
    
    public Map<String, T> getProperties()
    {
        
        List<T> list = super.getAll();
        
        Map<String, T> map = new HashMap<String, T>( list.size() );
        
        
        for ( T t: list )
        {
            t.fileOldValue();
            map.put( t.getName(), t );
        }
        
        return map;
    }
    
    @Transactional( readOnly = false )    
    public void save( Map<String, T> properties )
    {
        Set<String> keySet = properties.keySet();
        for ( String key: keySet )
        {
            T property = properties.get( key );
            properties.put( key, this.save( property ) );
        }
    }
}
