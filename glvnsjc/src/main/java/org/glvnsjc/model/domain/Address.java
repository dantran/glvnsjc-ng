package org.glvnsjc.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.glvnsjc.model.BaseObject;

/**
 * This class is used to represent an address with address,
 * city, province and postal-code information.
 *
 * Credit to AppFuse
 */
@Embeddable
public class Address
    extends BaseObject
    implements Serializable
{
    private static final long serialVersionUID = 3617859655330969141L;

    protected String address;

    protected String city;

    protected String province;

    protected String country;

    protected String postalCode;

    @Column(length = 150)
    public String getAddress()
    {
        return address;
    }

    @Column(nullable = true, length = 50)
    public String getCity()
    {
        return city;
    }

    @Column(length = 100)
    public String getProvince()
    {
        return province;
    }

    @Column(length = 100)
    public String getCountry()
    {
        return country;
    }

    @Column(name = "postal_code", nullable = true, length = 15)
    public String getPostalCode()
    {
        return postalCode;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    public void setPostalCode( String postalCode )
    {
        this.postalCode = postalCode;
    }

    public void setProvince( String province )
    {
        this.province = province;
    }

    public boolean equals( Object o )
    {
        if ( this == o )
            return true;
        if ( !( o instanceof Address ) )
            return false;

        final Address address1 = (Address) o;

        return this.hashCode() == address1.hashCode();
    }

    public int hashCode()
    {
        int result;
        result = ( getAddress() != null ? getAddress().hashCode() : 0 );
        result = 29 * result + ( getCity() != null ? getCity().hashCode() : 0 );
        result = 29 * result + ( getProvince() != null ? getProvince().hashCode() : 0 );
        result = 29 * result + ( getCountry() != null ? getCountry().hashCode() : 0 );
        result = 29 * result + ( getPostalCode() != null ? getPostalCode().hashCode() : 0 );
        return result;
    }

    public String toString()
    {
        return new ToStringBuilder( this, ToStringStyle.MULTI_LINE_STYLE ).append( "country", this.getCountry() )
            .append( "address", this.getAddress() ).append( "province", this.getProvince() )
            .append( "postalCode", this.getPostalCode() ).append( "city", this.getCity() ).toString();
    }
}
