package org.glvnsjc.model.domain;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.glvnsjc.model.NamedEntity;
import org.springframework.security.GrantedAuthority;

@Entity
public class Role
    extends NamedEntity
    implements GrantedAuthority
{
    
    private static final long serialVersionUID = 1L;
    
    public Role()
    {
        super();
    }
    
    /**
     * Create a new instance and set the name.
     * @param name name of the role.
     */
    public Role( final String name )
    {
        super( name );
    }

    /**
     * @see org.springframework.security.GrantedAuthority#getAuthority()
     * @return the name property (getAuthority required by Acegi's GrantedAuthority interface)
     */
    @Transient
    public String getAuthority()
    {
        return getName();
    }

    public int compareTo( Object o )
    {
        return ( equals( o ) ? 0 : -1 );
    }

}
