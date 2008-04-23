package org.glvnsjc.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;

/**
 * Root of all named POJOs
 * @author dtran
 *
 */
@MappedSuperclass
public abstract class NamedEntity
    extends BaseEntity
{
    @Column(nullable = false, unique = true )
    private String name;
    
    private String description;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = StringUtils.trim( name );
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }
}
