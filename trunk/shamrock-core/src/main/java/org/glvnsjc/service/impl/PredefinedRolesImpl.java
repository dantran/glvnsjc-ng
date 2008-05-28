package org.glvnsjc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.glvnsjc.model.LabelValue;
import org.glvnsjc.model.domain.Role;
import org.glvnsjc.model.domain.RoleType;
import org.glvnsjc.service.PredefinedRoles;
import org.glvnsjc.service.RoleManager;
import org.springframework.stereotype.Service;

@Service( "predefinedRoles" )
public class PredefinedRolesImpl
    implements PredefinedRoles
{
    private Map<String, Role> roles = new HashMap<String, Role>();
    
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
            roles.put( roleType.name(), role );
        }
    }
    
    
    public List<LabelValue> getAllRolesWithLabels()
    {
        List<LabelValue> list = new ArrayList<LabelValue>();

        for ( RoleType type: RoleType.values() )
        {
            Role role = roles.get( type.name() );
            list.add( new LabelValue( role.getName(), role.getName() ) );
        }

        return list;
    }


    @Override
    public Role getRoleByName( String name )
    {
        return roles.get( name );
    }


    @Override
    public Role getRoleByType( RoleType type )
    {
        return roles.get(  type.name() );
    }
    
}
