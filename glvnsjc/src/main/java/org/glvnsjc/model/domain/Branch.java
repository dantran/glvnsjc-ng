package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.glvnsjc.model.NamedEntity;

@Entity
public class Branch
extends NamedEntity
{
    private static final long serialVersionUID = 1L;
    
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
}
