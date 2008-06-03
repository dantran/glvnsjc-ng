package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * Manage School account and school configuration
 * @author dtran
 *
 */

@Entity
public class SchoolAdmin
    extends User
{
    private static final long serialVersionUID = 1L;
    
    @ManyToMany
    private Set<School> schools = new HashSet<School>();

    public Set<School> getSchools()
    {
        return schools;
    }

    public void setSchools( Set<School> managedSchools )
    {
        this.schools = managedSchools;
    }
    
    public void addSchool( School school )
    {
        this.schools.add( school );
    }

    
}
