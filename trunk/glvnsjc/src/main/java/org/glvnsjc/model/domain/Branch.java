package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.glvnsjc.model.NamedEntity;

@Entity
public class Branch
extends NamedEntity
{
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn( nullable=false)
    private School parentSchool;
    
    @OneToMany
    private Set<Clazz> classes = new HashSet<Clazz>();

    public Set<Clazz> getClasses()
    {
        return classes;
    }

    public void setClasses( Set<Clazz> classes )
    {
        this.classes = classes;
    }
    
    public void addCourses( Clazz clazz )
    {
        this.classes.add( clazz );
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
