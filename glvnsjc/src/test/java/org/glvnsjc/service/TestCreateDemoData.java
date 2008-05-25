package org.glvnsjc.service;

import javax.annotation.Resource;

import org.glvnsjc.service.demo.CreateDemoData;

public class TestCreateDemoData
    extends AbstractServiceTest
{
    @Resource
    private CreateDemoData createSchoolDemo;
    
    public void testCreateDemoData()
        throws Exception
    {
        createSchoolDemo.init();
    }
}
