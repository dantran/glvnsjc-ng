package org.glvnsjc.webapp.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.myfaces.orchestra.conversation.annotations.ConversationName;
import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.SchoolManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("conversation.access")
@ConversationName("mainSchools")
public class MainSchoolList
    extends BasePage
{

    @Resource
    private SchoolManager schoolManager;
    
    /////////////////////////////////////////////////////////////////////////////////////////
    
    private List<School> mainSchools = null;
    
    //////////////////////////////////////////////////////////////////////////////////////////

    @PostConstruct
    public void loadMainSchools()
    {
        this.mainSchools = schoolManager.getMainSchools();
    }
    
   
    public List<School> getMainSchools()
    {
        return this.mainSchools;
    }

    
}
