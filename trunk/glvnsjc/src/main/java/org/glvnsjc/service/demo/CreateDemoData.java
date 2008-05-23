package org.glvnsjc.service.demo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.GenericNameManager;
import org.glvnsjc.service.NameExistsException;
import org.glvnsjc.service.RoleManager;
import org.glvnsjc.service.UserManager;
import org.springframework.stereotype.Component;

@Component
public class CreateDemoData
{
    @Resource
    private UserManager userManager;
    @Resource
    private RoleManager roleManager;
    
    @Resource
    private GenericNameManager<School, Long> schoolManager;
    
    @PostConstruct
    public void init()
        throws NameExistsException
    {
        School school = new School();
        school.setName( "GLVNSJC" );
        school.setDescription( "Giao Ly Viet Ngu at Sanjose" );
        school = schoolManager.save( school );
    }
}
