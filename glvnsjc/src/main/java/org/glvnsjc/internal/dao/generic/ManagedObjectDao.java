/**
 * 
 */
package org.glvnsjc.internal.dao.generic;

import java.io.Serializable;

import org.glvnsjc.model.ManagedObject;

/**
 * @author dtran
 *
 */
public interface ManagedObjectDao<T extends ManagedObject, PK extends Serializable>
    extends GenericDao<T, PK >
{
    public T getByName( String name );

    public boolean nameExists( String name );
    
    public T getByGuid( String guid );
    
}
