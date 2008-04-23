package org.glvnsjc.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public abstract class PropertyEntity
    extends NamedEntity
{
    
    private String value;
    
    @Transient
    private String oldValue;

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    @XmlTransient
    public String getOldValue()
    {
        return oldValue;
    }

    public void setOldValue( String oldValue )
    {
        this.oldValue = oldValue;
    }
    
    public void fileOldValue()
    {
        if ( this.oldValue == null )
        {
            this.oldValue = this.value;
        }
    }
    
    public boolean isDirty()
    {
        if ( this.value != null )
        {
            return ! this.value.equals( this.oldValue );
        }
        else
        {
            return  ( this.oldValue != null );
        }
    }
}
