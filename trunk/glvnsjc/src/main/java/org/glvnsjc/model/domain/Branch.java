package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.glvnsjc.model.ManagedObject;

@Entity
public class Branch
    extends ManagedObject
{
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private Set<Course> courses = new HashSet<Course>();

    public Set<Course> getCourses()
    {
        return courses;
    }

    public void setCourses( Set<Course> courses )
    {
        this.courses = courses;
    }
}
