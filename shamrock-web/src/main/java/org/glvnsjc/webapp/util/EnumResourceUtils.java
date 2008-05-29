package org.glvnsjc.webapp.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class EnumResourceUtils
{
    private static final String ENUM_RESOURCE_BUNDLE_NAME = "enums";

    /**
     * get resource message of an enum value in the enum bundle
     * @param facesContext
     * @param en
     * @return
     */
    public static <T extends Enum<T>> String getMessage( FacesContext facesContext, T en )
    {
        Class<T> c = en.getDeclaringClass();
        ResourceBundle rb = getResourceBundle( facesContext );
        String fullEnumPath = c.getName() + "." + en.name();
        String convertedString = rb.getString( fullEnumPath );
        return convertedString;
    }

    /**
     * get resource message in the enum bundle
     * @param facesContext
     * @param key
     * @return
     */
    public static String getMessage( FacesContext facesContext, String key )
    {
        return getResourceBundle( facesContext ).getString( key );
    }

    /**
     * get selection list of a enum class with the first one having user defined key
     * like All/None/Unknown/Null
     * @param facesContext
     * @param enumClass
     * @param nullEnumResourceKey
     * @return
     */
    public static <T extends Enum<T>> List<SelectItem> getSelectItems( FacesContext facesContext, Class<T> enumClass,
                                                   String nullEnumResourceKey )
    {
        if ( enumClass == null )
        {
            throw new IllegalArgumentException( "invalid enum class:" + enumClass );
        }

        T[] enums = (T[]) enumClass.getEnumConstants();

        ArrayList<SelectItem> items = new ArrayList<SelectItem>( enums.length + 1 );
        items.add( new SelectItem( null, getMessage( facesContext, nullEnumResourceKey ) ) );
        for ( T en : enums )
        {
            items.add( new SelectItem( en, getMessage( facesContext, en ) ) );
        }

        return items;

    }

    /**
     * get selection list of a enum class, showing only the supported Enum values
     * with the first one having user defined key like All/None/Unknown/Null if the list is EMPTY
     * @param facesContext
     * @param enumClass
     * @param supportedEnums
     * @param nullEnumResourceKey
     * @return
     */
    public static <T extends Enum<T>> List<SelectItem> getSelectItems( FacesContext facesContext, Class<T> enumClass, Collection<T> supportedEnums, String nullEnumResourceKey )
    {
        if ( supportedEnums == null || supportedEnums.isEmpty() )
        {
            ArrayList<SelectItem> items = new ArrayList<SelectItem>( 1 );
            items.add( new SelectItem( null, getMessage( facesContext, nullEnumResourceKey ) ) );
            return items;
        }
        else
        {
            T[] enums = (T[]) enumClass.getEnumConstants();
            ArrayList<SelectItem> items = new ArrayList<SelectItem>();
            for ( T en : enums )
            {
                if ( supportedEnums.contains( en ))
                {
                    items.add( new SelectItem( en, getMessage( facesContext, en ) ) );
                }
            }

            return items;
        }
    }

    /**
     * get selection list of a enum class
     * @param facesContext
     * @param enumClass
     * @return list of SelectItems
     */
    public static <T extends Enum<T>> List<SelectItem> getSelectItems( FacesContext facesContext, Class<T> enumClass )
    {
        if ( enumClass == null )
        {
            throw new IllegalArgumentException( "invalid enum class:" + enumClass );
        }

        T[] enums = (T[]) enumClass.getEnumConstants();

        ArrayList<SelectItem> items = new ArrayList<SelectItem>( enums.length );
        for ( T en : enums )
        {
            items.add( new SelectItem( en, getMessage( facesContext, en ) ) );
        }

        return items;

    }

    
    /**
     * get ResourceBundle from face context
     * @param facesContext
     * @return
     */
    private static ResourceBundle getResourceBundle( FacesContext facesContext )
    {
        return facesContext.getApplication().getResourceBundle( facesContext, ENUM_RESOURCE_BUNDLE_NAME );
    }
}
