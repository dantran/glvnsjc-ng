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
   extends BasePage
{
    
    @Resource
    private SchoolManager schoolManager;
    
    private Long selectedSchoolId;
        
    private School selectedSchool;
    
    public List<School> getMainSchools()
    {
        return schoolManager.getMainSchools();
    }
    
    public String prepareEdit()
    {
        this.selectedSchool = this.schoolManager.get( this.selectedSchoolId);
        return "displayModify";
    }
    
    public String prepareDelete()
    {
        this.selectedSchool = this.schoolManager.get( this.selectedSchoolId );
        return "displayDelete";
    }

    public School getSelectedSchool()
    {
        return this.schoolManager.get( selectedSchoolId );
    }
    
    public boolean isRenderSelectedSchool()
    {
        return this.selectedSchoolId != null;
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
