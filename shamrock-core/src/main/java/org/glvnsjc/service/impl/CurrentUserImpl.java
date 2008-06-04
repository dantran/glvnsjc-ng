package org.glvnsjc.service.impl;

import javax.annotation.Resource;

import org.glvnsjc.model.domain.Role;
import org.glvnsjc.model.domain.RoleType;
import org.glvnsjc.model.domain.User;
import org.glvnsjc.service.CurrentUser;
import org.glvnsjc.service.PredefinedRoles;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component( "currentUser")
public class CurrentUserImpl
    implements CurrentUser
{
    
    @Resource
    private PredefinedRoles predefinedRoles;
    
    private User cacheUser = null;
    
    public User getUserDetails()
    {
        if ( cacheUser != null )
        {
            return cacheUser;
        }
        //goto to thread local storage to get principal

        Authentication au = SecurityContextHolder.getContext().getAuthentication();

        if ( au != null )
        {
            cacheUser = (User) au.getPrincipal();
        }

        return cacheUser;
    }
    
    public boolean isInRole( RoleType roleType )
    {
        Role role = this.predefinedRoles.getRoleByType( roleType );
        
        return getUserDetails().getRoles().contains( role );
    }
    
    public boolean isSystemAdmin()
    {
        return isInRole( RoleType.ROLE_ADMIN );
    }

    public boolean isSchoolAdmin()
    {
        return isInRole( RoleType.ROLE_SCHOOL_ADMIN );
    }    

    public boolean isInstructor()
    {
        return isInRole( RoleType.ROLE_INSTRUCTOR );
    }    

    public boolean isStudent()
    {
        return isInRole( RoleType.ROLE_STUDENT);
    }    
}
