package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.glvnsjc.model.NamedEntity;

/**
 * School can have many branches, top level school has no parent
 * a branch must have a parent
 * @author dtran
 *
 */
@Entity
public class School
    extends NamedEntity
{
    private static final long serialVersionUID = 1L;
        
    @OneToMany
    private Set<Branch> branches = new HashSet<Branch>();

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
    
    public void addBranch( Branch branch )
    {
        this.branches.add( branch );
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
