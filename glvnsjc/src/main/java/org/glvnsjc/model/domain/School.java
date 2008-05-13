package org.glvnsjc.model.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.glvnsjc.model.ManagedObject;

@Entity
public class School
    extends ManagedObject
{
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private Set<Branch> branches;

    @OneToMany
    private Set<Term> terms;
    
    public Set<Branch> getBranches()
    {
        return branches;
    }

    public void setBranches( Set<Branch> branches )
    {
        this.branches = branches;
    }

    public Set<Term> getTerms()
    {
        return terms;
    }

    public void setTerms( Set<Term> terms )
    {
        this.terms = terms;
    }
}
