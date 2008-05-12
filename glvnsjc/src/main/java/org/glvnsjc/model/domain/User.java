package org.glvnsjc.model.domain;

import javax.persistence.Entity;

import org.glvnsjc.model.ManagedObject;

@Entity
public class User
    extends ManagedObject
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
