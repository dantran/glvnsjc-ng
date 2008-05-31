package org.glvnsjc.webapp.util;

import org.apache.myfaces.shared.util.MessageUtils;

public enum CrudType {

    ADD,

    EDIT,

    DELETE;

    private static String BASE_BUNDLE = "org.glvnsjc.webapp.resource.ApplicationResources";

    public static CrudType get( String message )
    {
        String bundleMsg = MessageUtils.getMessage( BASE_BUNDLE, "crud.remove", null ).toString();
        if ( message.equals( bundleMsg ) )
        {
            return DELETE;
        }

        return null;
    }
}
