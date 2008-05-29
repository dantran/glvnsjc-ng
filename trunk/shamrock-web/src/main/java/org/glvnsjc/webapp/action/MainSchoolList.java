package org.glvnsjc.webapp.action;

import java.util.List;

import javax.annotation.Resource;

import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.SchoolManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class MainSchoolList
    extends BasePage
{

    @Resource
    private SchoolManager schoolManager;

    private Long selectedSchoolId;

    private String selectedSchoolAction;

    private School selectedSchool;

    public List<School> getMainSchools()
    {
        return schoolManager.getMainSchools();
    }

    public School getSelectedSchool()
    {
        if ( selectedSchoolId != null )
        {
            return this.selectedSchool;
        }

        //even though we don't render the school detail but still need a dummy school for loading purpose
        return new School();
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
        if ( selectedSchoolId.intValue() == 0 )
        {
            this.selectedSchool = new School();
        }
        else
        {
            this.selectedSchool = this.schoolManager.get( selectedSchoolId );
        }

    }

    public String getSelectedSchoolAction()
    {
        return selectedSchoolAction;
    }

    public void setSelectedSchoolAction( String selectedSchoolAction )
    {
        this.selectedSchoolAction = selectedSchoolAction;
    }

    public void prepareAdd( javax.faces.event.ActionEvent event )
    {
        this.selectedSchoolId = new Long( 0 );
        this.selectedSchool = new School();
    }

    public void edit( javax.faces.event.ActionEvent event )
    {

        //save the school

        //reset the selectSchoolId to trigger the view to hide the school details block
        this.selectedSchoolId = null;

    }

    public void setSelectedSchool( School selectedSchool )
    {
        this.selectedSchool = selectedSchool;
    }

    public boolean isReadonly()
    {
        return "del".equals( this.selectedSchoolAction );
    }

    public boolean isValidateNeeded()
    {
        return isReadonly();
    }

}
