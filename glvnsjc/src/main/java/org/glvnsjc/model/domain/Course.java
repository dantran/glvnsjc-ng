package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.glvnsjc.model.NamedEntity;

@Entity
public class Course
    extends NamedEntity
{
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Term term;

    @ManyToMany
    private Set<Instructor> instructors = new HashSet<Instructor>();

    @ManyToMany
    private Set<Student> students = new HashSet<Student>();
    
    @OneToMany
    private Set<Score> scores = new HashSet<Score>();

    public Branch getBranch()
    {
        return branch;
    }

    public void setBranch( Branch branch )
    {
        this.branch = branch;
    }

    public Term getTerm()
    {
        return term;
    }

    public void setTerm( Term term )
    {
        this.term = term;
    }

    public Set<Score> getScores()
    {
        return scores;
    }

    public void setScores( Set<Score> scores )
    {
        this.scores = scores;
    }

    public Set<Instructor> getInstructors()
    {
        return instructors;
    }

    public void setInstructors( Set<Instructor> instructors )
    {
        this.instructors = instructors;
    }

    public Set<Student> getStudents()
    {
        return students;
    }

    public void setStudents( Set<Student> students )
    {
        this.students = students;
    }

    ////////////////////////////////////////////////////////////////////////////////

    /*
    @OneToMany
    private Set<Attendant> attendants = new HashSet<Attendant>();

    public Set<Attendant> getAttendants()
    {
        return attendants;
    }

    public void setAttendants( Set<Attendant> attendants )
    {
        this.attendants = attendants;
    }
    */
    
    
    ////////////////////////////////////////////////////////////////////////////////

}
