package org.glvnsjc.webapp.action;

import javax.annotation.Resource;

import org.glvnsjc.model.domain.RoleType;
import org.glvnsjc.service.CurrentUser;
import org.springframework.stereotype.Component;

//@Component
public class Navigator
{
    @Resource
    private CurrentUser currentUser;
    
    public boolean isSystemConfigurationEnable()
    {
        return currentUser.isInRole( RoleType.ROLE_ADMIN );
    }
}
