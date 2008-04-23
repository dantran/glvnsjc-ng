package org.glvnsjc.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Root of all POJOs
 * @author dtran
 *
 */
@MappedSuperclass
public abstract class BaseEntity
    implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId( Long id )
    {
        this.id = id;
    }

    public Long getId()
    {
        return this.id;
    }

}
