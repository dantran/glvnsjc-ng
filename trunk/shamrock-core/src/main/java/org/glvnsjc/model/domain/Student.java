package org.glvnsjc.model.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student
    extends User
{
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn( nullable = true )
    private Parent parent;

    public Parent getParent()
    {
        return parent;
    }

    public void setParent( Parent parent )
    {
        this.parent = parent;
    }
    

}
