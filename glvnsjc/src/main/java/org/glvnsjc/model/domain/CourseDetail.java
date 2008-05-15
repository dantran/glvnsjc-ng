package org.glvnsjc.model.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.glvnsjc.model.NamedEntity;

@Entity
@Inheritance( strategy=InheritanceType.JOINED )
public abstract class CourseDetail
    extends NamedEntity
{
    private static final long serialVersionUID = 1L;

    private Date date;
    
    @OneToOne
    private Student student;

    @OneToOne
    private Course course;

    public Course getCourse()
    {
        return course;
    }

    public void setCourse( Course course )
    {
        this.course = course;
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent( Student student )
    {
        this.student = student;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

}
