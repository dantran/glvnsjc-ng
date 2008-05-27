package org.glvnsjc.service;

import java.util.List;

import javax.annotation.Resource;

import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.demo.CreateDemoData;

public class TestCreateDemoData
    extends AbstractServiceTest
{
    @Resource
    private CreateDemoData createSchoolDemo;
    
    @Resource
    private SchoolManager schoolManager;
    
    public void testCreateDemoData()
        throws Exception
    {
        createSchoolDemo.init();
        
        List<School> schools = schoolManager.getMainSchools();
        
        assertEquals( 2, schools.size() );
    }
}
