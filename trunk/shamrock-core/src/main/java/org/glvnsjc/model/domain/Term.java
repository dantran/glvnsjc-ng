package org.glvnsjc.model.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    
    @ManyToOne
    @JoinColumn( nullable = false )
    private School school;
    
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

}
