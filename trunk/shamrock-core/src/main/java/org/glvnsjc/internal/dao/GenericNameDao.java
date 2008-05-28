/**
 * 
 */
package org.glvnsjc.internal.dao;

import java.io.Serializable;

import org.glvnsjc.model.NamedEntity;

/**
 * @author dtran
 *
 */
public interface GenericNameDao<T extends NamedEntity, PK extends Serializable>
    extends GenericDao<T, PK >
{
    public T getByName( String name );

    public boolean nameExists( String name );
    
}
