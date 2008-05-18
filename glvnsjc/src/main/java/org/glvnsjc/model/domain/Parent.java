package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Parent
    extends User
{
    private static final long serialVersionUID = 1L;

    @OneToMany
    private Set<Student> students = new HashSet<Student>();

    public Set<Student> getStudents()
    {
        return students;
    }

    public void setStudents( Set<Student> students )
    {
        this.students = students;
    }
    
    
}
