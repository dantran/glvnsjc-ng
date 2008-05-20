package org.glvnsjc.internal.startup;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.glvnsjc.model.domain.RoleType;
import org.glvnsjc.model.domain.Staff;
import org.glvnsjc.model.domain.User;
import org.glvnsjc.service.PredefinedRoles;
import org.glvnsjc.service.UserExistsException;
import org.glvnsjc.service.UserManager;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInit
{
    private static final String ADMIN_USER = "admin";
    
    @Resource
    private PredefinedRoles predefineRoles;
    
    @Resource
    private UserManager userManager;
    
    @PostConstruct
    public void init()
        throws UserExistsException
    {
        
        User adminUser = null;
        
        try
        {
            adminUser = userManager.getUserByUsername( ADMIN_USER );
        }
        catch ( UsernameNotFoundException e )
        {
            adminUser = new Staff();
            adminUser.setName( ADMIN_USER );
            adminUser.setPassword( ADMIN_USER );
            adminUser =  this.userManager.saveUser( adminUser );
            adminUser.getRoles().add( this.predefineRoles.getRoleByType( RoleType.ROLE_SYSTEM_ADMIN )  );
            this.userManager.saveUser( adminUser );
        }
        
    }
}