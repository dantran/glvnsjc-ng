package org.glvnsjc.internal.startup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.glvnsjc.model.LabelValue;
import org.glvnsjc.model.domain.Role;
import org.glvnsjc.model.domain.RoleType;
import org.glvnsjc.service.RoleManager;
import org.springframework.stereotype.Service;

@Service
public class UserRoleList
{
    private List<Role> roles = new ArrayList<Role>();
    
    @Resource
    private RoleManager roleManager;
    
    @PostConstruct
    public void init()
    {
        for ( RoleType roleType : RoleType.values() )
        {
            Role role = roleManager.getRole( roleType.name() );
            if ( role == null )
            {
                role = new Role( roleType.name() );
                role = roleManager.saveRole( role );
            }
            roles.add( role );
        }
    }
    
    public List<Role> getRoles()
    {
        return roles;
    }
    
    public List<LabelValue> getLabelRoles()
    {
        List<Role> roles = this.getRoles();
        
        List<LabelValue> list = new ArrayList<LabelValue>();

        for ( Role role1 : roles )
        {
            list.add( new LabelValue( role1.getName(), role1.getName() ) );
        }

        return list;
    }
    
}
