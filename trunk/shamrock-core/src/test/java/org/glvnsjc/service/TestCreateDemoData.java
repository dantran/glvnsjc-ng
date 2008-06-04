package org.glvnsjc.service;

import java.util.List;

import javax.annotation.Resource;

import org.glvnsjc.model.domain.School;
import org.glvnsjc.model.domain.User;
import org.glvnsjc.service.demo.CreateDemoData;

public class TestCreateDemoData
    extends AbstractServiceTest
{
    @Resource
    private CreateDemoData createSchoolDemo;
    
    @Resource
    private SchoolManager schoolManager;
    
    @Resource
    private UserManager userManager;
    
    
    public void testCreateDemoData()
        throws Exception
    {
        createSchoolDemo.init();
        
        //test main schools
        List<School> schools = schoolManager.getMainSchools();
        assertEquals( 2, schools.size() );
        
        //test all school + branches
        schools = schoolManager.getAll();
        assertEquals( 4, schools.size() );
        
        List<User> users = userManager.getUsers();
        assertEquals( 6, users.size() );
    }
}
