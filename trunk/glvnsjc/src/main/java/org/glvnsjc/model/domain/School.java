package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
        
    @OneToOne
    private School parentSchool;
    
    @OneToMany
    private Set<School> branches = new HashSet<School>();

    @OneToMany
    private Set<Term> terms;
    
    public Set<School> getBranches()
    {
        return branches;
    }

    public void setBranches( Set<School> branches )
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

    public School getParentSchool()
    {
        return parentSchool;
    }

    public void setParentSchool( School parentSchool )
    {
        this.parentSchool = parentSchool;
    }
}
