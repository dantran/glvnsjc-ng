package org.glvnsjc.webapp.action;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;

import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.SchoolManager;
import org.glvnsjc.webapp.util.CrudType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class SchoolDetail
{

    @Resource
    private SchoolManager schoolManager;
    
    /////////////////////////////////////////////////////////////////////////////////////////
    
    private Long selectedSchoolId;

    private String selectedSchoolAction;

    private School selectedSchool;
    
    private boolean rendered;
   
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    public School getSelectedSchool()
    {
        if ( selectedSchoolId != null )
        {
            return this.selectedSchool;
        }

        //even though we don't render the school detail but still need a dummy school for loading purpose
        return new School();
    }

    public void setSelectedSchool( School selectedSchool )
    {
        this.selectedSchool = selectedSchool;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
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
    
    ////////////////////////////////////////////////////////////////////////////////////////////

    public String getSelectedSchoolAction()
    {
        return selectedSchoolAction;
    }

    public void setSelectedSchoolAction( String selectedSchoolAction )
    {
        this.selectedSchoolAction = selectedSchoolAction;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isRendered()
    {
        return rendered;
    }    
    ////////////////////////////////////////////////////////////////////////////////////////////
 
    public boolean isReadOnly()
    {
        //FIXME: need to get this from resource
        
        return "Remove".equals( this.selectedSchoolAction );
    }
    
    /**
     * Trigger the view to display selected school detail
     * @return
     */
    public String prepareDetail( )
    {
        this.rendered = true;
        
        return null;
    }

    public String persistSchool( )
    {
        //handle pojo crud here
       
        
        this.rendered = false;
        
        return null;
    }



}
