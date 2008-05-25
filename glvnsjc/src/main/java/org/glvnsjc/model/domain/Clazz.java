package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.glvnsjc.model.NamedEntity;

/**
 * A class of a specific Course offered by a school in a academic term.
 * An instructor creates class activities and keep scores and attendants
 * @author dtran
 *
 */
@Entity
public class Clazz
    extends NamedEntity
{
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;
    
    /**
     * school that offer this course in academic term
     */
    @ManyToOne
    @JoinColumn(nullable = false)    
    private School school;

    @ManyToOne
    @JoinColumn(nullable = false)    
    private Term term;

    @ManyToMany
    private Set<Instructor> instructors = new HashSet<Instructor>();

    @ManyToMany
    private Set<Student> students = new HashSet<Student>();
    
    @OneToMany
    private Set<Score> scores = new HashSet<Score>();

    public School getSchool()
    {
        return school;
    }

    public void setSchool( School school )
    {
        this.school = school;
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
    
    public void addInstructor( Instructor instructor )
    {
        this.instructors.add( instructor );
    }

    public Set<Student> getStudents()
    {
        return students;
    }

    public void setStudents( Set<Student> students )
    {
        this.students = students;
    }

    public void addStudent( Student student )
    {
        this.students.add( student );
    }
    
    public Course getCourse()
    {
        return course;
    }

    public void setCourse( Course course )
    {
        this.course = course;
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
