package org.glvnsjc.service;

import org.glvnsjc.model.domain.RoleType;
import org.glvnsjc.model.domain.User;

public interface CurrentUser
{
    User getUserDetails();

    /**
     * check for authorization
     * @param roleType
     * @return
     */
    boolean isInRole( RoleType roleType );
}
