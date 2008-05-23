package org.glvnsjc.model.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.glvnsjc.model.NamedEntity;

@Entity
public class Course
    extends NamedEntity
{
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private School school;


    public School getSchool()
    {
        return school;
    }

    public void setSchool( School school )
    {
        this.school = school;
    }

}
