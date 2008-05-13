package org.glvnsjc.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance( strategy=InheritanceType.JOINED )
public abstract class ManagedObject
    extends NamedEntity
{
    
    @Column(nullable = false, unique = true)
    private String guid;    
    
            
    ////////////////////////////////////////////////////////////////////////////
    
    public String getGuid()
    {
        return guid;
    }

    public void setGuid( String guid )
    {
        this.guid = guid;
    }

    /*
    @ManyToOne
    private ManagedGroup group;
    
    public ManagedGroup getGroup()
    {
        return this.group;
    }
    
    public void setGroup( ManagedGroup group )
    {
        this.group = group;
    }     
    */      
}
