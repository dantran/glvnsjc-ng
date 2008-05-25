package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.glvnsjc.model.NamedEntity;

/**
 * <ul>
 *   <li>School can have many branches, top level school has no parent.
 *       A branch must have a parent.</li>
 *   <li>An instructor can belong to multiple schools.</li>
 *   <li>Courses only belong to parent school.</li>
 * </ul>
 * @author dtran
 *
 */
@Entity
public class School
    extends NamedEntity
{
    private static final long serialVersionUID = 1L;
        
    @ManyToOne
    @JoinColumn( nullable=true)
    private School parentSchool;
    
    @OneToMany
    private Set<School> branches = new HashSet<School>();

    @ManyToMany
    private Set<Instructor> instructors = new HashSet<Instructor>();
            
    @OneToMany
    private Set<Term> terms = new HashSet<Term>();

    @OneToMany
    private Set<Course> courses = new HashSet<Course>();
    
    public Set<School> getBranches()
    {
        return branches;
    }

    public void setBranches( Set<School> branches )
    {
        this.branches = branches;
    }
    
    public void addBranch( School branch )
    {
        this.branches.add( branch );
    }

    public Set<Term> getTerms()
    {
        return terms;
    }

    public void setTerms( Set<Term> terms )
    {
        this.terms = terms;
    }
    
    public void addTerm( Term term )
    {
        this.terms.add(  term );
    }

    public Set<Course> getCourses()
    {
        return courses;
    }

    public void setCourses( Set<Course> courses )
    {
        this.courses = courses;
    }
    
    public void addCourse( Course course )
    {
        this.courses.add(  course );
    }

    public School getParentSchool()
    {
        return parentSchool;
    }

    public void setParentSchool( School parentSchool )
    {
        this.parentSchool = parentSchool;
    }


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
    
    /**
     * 
     * @throws IllegalStateException
     */
    public void validate()
        throws IllegalStateException
    {
        boolean valid = true;
        
        if ( branches.size() != 0 )
        {
            valid = ( this.parentSchool == null );
            
            if ( valid ) 
            {
                for ( School branch: branches )
                {
                    valid = branch.getParentSchool() != null;
                    if ( !valid ) 
                    {
                        break;
                    }
                }
            }
        }
           
        if ( ! valid )
        {
            throw new IllegalStateException( "Invalide school settings." );
        }
    }
    
    
    @Transient
    public Term getCurrentTerm()
    {
        Term currentTerm = null;
        
        for ( Term term: terms )
        {
            if ( ! term.isClosed() )
            {
                currentTerm = term;
                break;
            }
        }
        
        return currentTerm;
    }
}
