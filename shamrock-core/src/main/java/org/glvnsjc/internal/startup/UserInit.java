package org.glvnsjc.internal.startup;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.glvnsjc.model.domain.RoleType;
import org.glvnsjc.model.domain.SystemAdmin;
import org.glvnsjc.model.domain.User;
import org.glvnsjc.service.NameExistsException;
import org.glvnsjc.service.PredefinedRoles;
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
    public void afterPropertiesSet()
        throws NameExistsException
    {
        
        User adminUser = null;
        
        try
        {
            adminUser = userManager.getUserByUsername( ADMIN_USER );
        }
        catch ( UsernameNotFoundException e )
        {
            adminUser = new SystemAdmin();
            adminUser.setEnabled( true );
            adminUser.setName( ADMIN_USER );
            adminUser.setPassword( ADMIN_USER );
            
            //adminUser.getRoles().add( this.predefineRoles.getRoleByType( RoleType.ROLE_ADMIN )  );
            
            for ( int i = 0 ; i < RoleType.values().length; ++i  )
            {
                adminUser.getRoles().add( this.predefineRoles.getRoleByType( RoleType.values()[i] )  );
            }
            
            adminUser =  this.userManager.saveUser( adminUser );
        }
        
    }
}
