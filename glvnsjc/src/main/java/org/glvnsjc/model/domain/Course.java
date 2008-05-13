package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.glvnsjc.model.ManagedObject;

@Entity
public class Course
    extends ManagedObject
{
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private Branch branch;
    
    @ManyToOne
    private Term term;
    

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

    ////////////////////////////////////////////////////////////////////////////////
    
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
    
    ////////////////////////////////////////////////////////////////////////////////
    
}
