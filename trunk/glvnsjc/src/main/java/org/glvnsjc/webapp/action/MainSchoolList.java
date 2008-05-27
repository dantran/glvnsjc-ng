package org.glvnsjc.webapp.action;

import java.util.List;

import javax.annotation.Resource;

import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.SchoolManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope( "request")
public class MainSchoolList
{
    
    @Resource
    private SchoolManager schoolManager;
    
    private Long selectedSchoolId;
    
    public List<School> getMainSchools()
    {
        return schoolManager.getMainSchools();
    }
    
    public String prepareEdit()
    {
        return "displayModify";
    }
    
    public String prepareDelete()
    {
        return "displayDelete";
    }


    public Long getSelectedSchoolId()
    {
        return selectedSchoolId;
    }

    public void setSelectedSchoolId( Long selectedSchoolId )
    {
        this.selectedSchoolId = selectedSchoolId;
    }
    
}
