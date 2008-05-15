package org.glvnsjc.model.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.glvnsjc.model.NamedEntity;

@Entity
@Inheritance( strategy=InheritanceType.JOINED )
public abstract class User
    extends NamedEntity
{
    private static final long serialVersionUID = 1L;
    
    private Address address;
    
    private String password;

    public Address getAddress()
    {
        return address;
    }

    public void setAddress( Address address )
    {
        this.address = address;
    }

    public String getUsername()
    {
        return this.getName();
    }
    
    public void setUsername( String username )
    {
        super.setName( username );
    }
    
    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }
    
    
}
