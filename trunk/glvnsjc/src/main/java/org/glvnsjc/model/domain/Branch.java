package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.glvnsjc.model.NamedEntity;

@Entity
public class Branch
extends NamedEntity
{
    private static final long serialVersionUID = 1L;
    
    @OneToOne
    private School parentSchool;
    
    @OneToMany
    private Set<Clazz> courses = new HashSet<Clazz>();

    public Set<Clazz> getCourses()
    {
        return courses;
    }

    public void setCourses( Set<Clazz> courses )
    {
        this.courses = courses;
    }
    
    public School getParentSchool()
    {
        return parentSchool;
    }

    public void setParentSchool( School parentSchool )
    {
        this.parentSchool = parentSchool;
    }

}
