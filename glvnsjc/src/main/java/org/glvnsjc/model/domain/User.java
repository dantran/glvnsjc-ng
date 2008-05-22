package org.glvnsjc.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.glvnsjc.model.NamedEntity;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User
    extends NamedEntity
    implements UserDetails
{
    private static final long serialVersionUID = 1L;

    private String firstName;
    
    private String middleName;
    
    private String lastName;
    
    private String email; // required; unique

    private String phoneNumber;

    @Embedded
    private Address address;

    private String password;
    
    @Transient
    private String confirmPassword;
    
    private String passwordHint;
    

    private boolean credentialsExpired;

    private boolean accountLocked;

    private boolean accountExpired;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<Role>();

    public Address getAddress()
    {
        return address;
    }

    public void setAddress( Address address )
    {
        this.address = address;
    }

    /**
     * Returns the full name.
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName()
    {
        return firstName + ' ' + middleName + ' ' + lastName;
    }

    /**
     * Alias to getName();
     */
    @Transient
    public String getUsername()
    {
        return this.getName();
    }

    public void setUsername( String username )
    {
        super.setName( username );
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
     * @return GrantedAuthority[] an array of roles.
     */
    @Transient
    public GrantedAuthority[] getAuthorities()
    {
        return roles.toArray( new GrantedAuthority[0] );
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public boolean isAccountExpired()
    {
        return accountExpired;
    }

    /**
     * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
     */
    @Transient
    public boolean isAccountNonExpired()
    {
        return !isAccountExpired();
    }

    public boolean isAccountLocked()
    {
        return accountLocked;
    }

    /**
     * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
     */
    @Transient
    public boolean isAccountNonLocked()
    {
        return !isAccountLocked();
    }

    public boolean isCredentialsExpired()
    {
        return credentialsExpired;
    }

    /**
     * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Transient
    public boolean isCredentialsNonExpired()
    {
        return !credentialsExpired;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles( Set<Role> roles )
    {
        this.roles = roles;
    }
    
    public void addRole( Role role )
    {
        this.roles.add( role );
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber )
    {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName( String middleName )
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword( String confirmPassword )
    {
        this.confirmPassword = confirmPassword;
    }

    public String getPasswordHint()
    {
        return passwordHint;
    }

    public void setPasswordHint( String passwordHint )
    {
        this.passwordHint = passwordHint;
    }

    public void setEnabled( boolean enabled )
    {
        this.enabled = enabled;
    }
    
}
