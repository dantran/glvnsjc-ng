package org.glvnsjc.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Root of all named POJOs
 * @author dtran
 *
 */
@MappedSuperclass
public abstract class NamedEntity
    extends BaseEntity
{
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Version
    private Integer version;
    
    public NamedEntity()
    {
        
    }
    
    public NamedEntity( final String name )
    {
        this.setName( name);
    }  
    
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

    /**
     * {@inheritDoc}
     * @fixme 
     */
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( !( o instanceof NamedEntity ) )
        {
            return false;
        }

        final NamedEntity entity = (NamedEntity) o;

        return !( this.getName() != null ? !this.getName().equals( entity.getName() ) : entity.getName() != null );

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode()
    {
        return ( this.getName() != null ? this.getName().hashCode() : 0 );
    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        return new ToStringBuilder( this, ToStringStyle.SIMPLE_STYLE ).append( this.getName() ).toString();
    }

    public Integer getVersion()
    {
        return version;
    }


}
