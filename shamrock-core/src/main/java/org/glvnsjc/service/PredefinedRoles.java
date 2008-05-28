package org.glvnsjc.service;

import java.util.List;

import org.glvnsjc.model.LabelValue;
import org.glvnsjc.model.domain.Role;
import org.glvnsjc.model.domain.RoleType;

public interface PredefinedRoles
{
    Role getRoleByName( String name );
    
    Role getRoleByType( RoleType type );
    
    List<LabelValue> getAllRolesWithLabels();
}
