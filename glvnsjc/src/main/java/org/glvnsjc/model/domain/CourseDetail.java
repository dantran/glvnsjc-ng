package org.glvnsjc.model.domain;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.glvnsjc.model.ManagedObject;

@MappedSuperclass
public abstract class CourseDetail
    extends ManagedObject
{
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private Student student;
    
    @ManyToOne
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
    
}
