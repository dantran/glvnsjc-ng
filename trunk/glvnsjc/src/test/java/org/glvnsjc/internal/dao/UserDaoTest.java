package org.glvnsjc.internal.dao;

import org.glvnsjc.model.domain.User;

public class UserDaoTest
    extends BaseDaoTestCase
{
    private ManagedObjectDao<User, Long> dao;
    
    public void setUserDao( ManagedObjectDao<User, Long> dao )
    {
        this.dao = dao;
    }
    
    //////////////////////////////////////////////////////////////////
    public void testNothing()
    {
        User user = new User();
        user.setName( "user" );
        User savedUser = this.dao.save( user );
        assertNotNull( savedUser.getId() );
        assertEquals( user.getName(), savedUser.getName() );
        
    }


}
