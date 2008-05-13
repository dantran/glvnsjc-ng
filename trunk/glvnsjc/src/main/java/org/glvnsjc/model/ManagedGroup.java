package org.glvnsjc.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ManagedGroup
    extends ManagedObject
{
    private static final long serialVersionUID = 1L;

    /*
    @OneToMany( mappedBy="group")
    private Set<ManagedObject> managedObjects;
    
    /////////////////////////////////////////////////////////////////////////////
    
    public Set<ManagedObject> getManagedObjects()
    {
        return this.managedObjects;
    }
    */
}
