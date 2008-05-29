package org.glvnsjc.webapp.util;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.myfaces.shared_tomahawk.util.ClassUtils;

/**
 * converts an enum in a way that makes the conversion reversible between view and backing bean
 * <ul>
 * <li>input: uses its classname and ordinal, reversible<li>
 * <li>else: uses its name, non reversible<li>
 * </ul>
 * 
 */
public class EnumConverter
    implements Converter
{
    /* Taken from tomahawk sandbox */

    @SuppressWarnings("unchecked")
    public Object getAsObject( FacesContext context, UIComponent component, String value )
        throws ConverterException
    {
        if ( value == null || value.length() < 1 )
        {
            return null;
        }

        int pos = value.indexOf( '@' );
        if ( pos < 0 )
        {
            throw new IllegalArgumentException( value + " do not point to an enum" );
        }

        String clazz = value.substring( 0, pos );
        int ordinal = Integer.parseInt( value.substring( pos + 1 ), 10 );

        try
        {
            Enum[] enums = (Enum[]) ClassUtils.classForName( clazz ).getEnumConstants();
            if ( enums.length >= ordinal )
            {
                return enums[ordinal];
            }
        }
        catch ( ClassNotFoundException e1 )
        {
            throw new RuntimeException( e1 );
        }

        throw new IllegalArgumentException( "ordinal " + ordinal + " not found in enum " + clazz );
    }

    public String getAsString( FacesContext context, UIComponent component, Object value )
        throws ConverterException
    {
        if ( value == null )
        {
            return "";
        }

        Enum e = (Enum) value;

        if ( component instanceof UIInput || UIInput.COMPONENT_FAMILY.equals( component.getFamily() ) )
        {
            return e.getClass().getName() + "@" + Integer.toString( e.ordinal(), 10 );
        }

        return e.toString();
    }
}
