package org.glvnsjc.model.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Student
    extends User
{
    private static final long serialVersionUID = 1L;
    
    @OneToOne
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
