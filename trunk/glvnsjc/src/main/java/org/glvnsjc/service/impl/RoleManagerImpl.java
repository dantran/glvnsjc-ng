package org.glvnsjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.glvnsjc.internal.dao.RoleDao;
import org.glvnsjc.model.domain.Role;
import org.glvnsjc.service.RoleManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of RoleManager interface.
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("roleManager")
@Transactional
public class RoleManagerImpl
    extends UniversalManagerImpl
    implements RoleManager
{
    @Resource
    private RoleDao roleDao;

    public void setRoleDao( RoleDao dao )
    {
        this.roleDao = dao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Role> getRoles( Role role )
    {
        return roleDao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public Role getRole( String rolename )
    {
        return roleDao.getRoleByName( rolename );
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = false)
    public Role saveRole( Role role )
    {
        return roleDao.save( role );
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = false)
    public void removeRole( String rolename )
    {
        roleDao.removeRole( rolename );
    }
}