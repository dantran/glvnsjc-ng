/**
 * 
 */
package org.glvnsjc.internal.dao;

import java.io.Serializable;
import java.util.Map;

import org.glvnsjc.model.PropertyEntity;

/**
 * @author dtran
 *
 */
public interface GenericPropertyDao<T extends PropertyEntity, PK extends Serializable>
    extends GenericNameDao<T, PK >
{
    T getByName( String name,  Class<T> clazz );
    
    Map<String, T> getProperties();
    
    void save( Map<String, T> properties );
}
