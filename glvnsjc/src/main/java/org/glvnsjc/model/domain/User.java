package org.glvnsjc.model.domain;

import javax.persistence.Entity;

import org.glvnsjc.model.ManagedObject;

@Entity
public class User
    extends ManagedObject
{
    private static final long serialVersionUID = 1L;
    
    private Address address;

    public Address getAddress()
    {
        return address;
    }

    public void setAddress( Address address )
    {
        this.address = address;
    }
    
    
}
