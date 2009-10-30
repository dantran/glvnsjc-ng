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
    @JoinColumn(nullable = false)
    private School parentSchool;

    public School getParentSchool()
    {
        return parentSchool;
    }

    public void setParentSchool( School parentSchool )
    {
        this.parentSchool = parentSchool;
    }

    @OneToMany
    private Set<Instructor> instructors = new HashSet<Instructor>();

    public Set<Instructor> getInstructors()
    {
        return instructors;
    }

    public void setInstructors( Set<Instructor> instructors )
    {
        this.instructors = instructors;
    }

    public void addInstructor( Instructor instructor )
    {
        this.instructors.add( instructor );
    }

}
