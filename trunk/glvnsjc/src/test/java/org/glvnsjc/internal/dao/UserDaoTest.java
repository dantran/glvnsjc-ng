package org.glvnsjc.internal.dao;

import org.glvnsjc.model.domain.Student;
import org.glvnsjc.model.domain.User;

public class UserDaoTest
    extends BaseDaoTestCase
{
    private GenericNameDao<User, Long> dao;
    
    public void setUserDao( GenericNameDao<User, Long> dao )
    {
        this.dao = dao;
    }
    
    //////////////////////////////////////////////////////////////////
    public void testNothing()
    {
        User user = new Student();
        user.setName( "user" );
        User savedUser = this.dao.save( user );
        assertNotNull( savedUser.getId() );
        assertEquals( user.getName(), savedUser.getName() );
    }

    public void testGetName()
    {
        User user = new Student();
        user.setName( "user" );
        User savedUser = this.dao.save( user );
        assertNotNull( savedUser.getId() );
        assertEquals( user.getName(), savedUser.getName() );
    }
}
