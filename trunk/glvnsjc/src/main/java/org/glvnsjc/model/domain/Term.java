package org.glvnsjc.model.domain;

import javax.persistence.Entity;

import org.glvnsjc.model.ManagedObject;

@Entity
public class Term
    extends ManagedObject
{
    private static final long serialVersionUID = 1L;
    
    private boolean closed;

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
    
}
