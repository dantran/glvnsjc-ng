package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.glvnsjc.model.NamedEntity;


/**
 * Academic Term
 * @author dtran
 *
 */

@Entity
public class Term
    extends NamedEntity
{
    private static final long serialVersionUID = 1L;

    private boolean closed;
    
    @OneToOne
    private School school;
    
    @OneToMany
    private Set<Clazz> courses = new HashSet<Clazz>();

    public boolean isClosed()
    {
        return closed;
    }
    
    public void setClosed( boolean closed )
    {
        this.closed = closed;
    }

    public void close()
    {
        setClosed( true );
    }

    public School getSchool()
    {
        return school;
    }

    public void setSchool( School school )
    {
        this.school = school;
    }

    public Set<Clazz> getCourses()
    {
        return courses;
    }

    public void setCourses( Set<Clazz> courses )
    {
        this.courses = courses;
    }

    
}
