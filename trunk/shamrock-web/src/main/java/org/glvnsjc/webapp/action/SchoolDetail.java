package org.glvnsjc.webapp.action;

import javax.annotation.Resource;

import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.SchoolManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class SchoolDetail
{

    @Resource
    private SchoolManager schoolManager;

    /////////////////////////////////////////////////////////////////////////////////////////

    private Long id;

    private String action;

    private School school;

    private boolean rendered;
    
    private boolean readOnly;

    ////////////////////////////////////////////////////////////////////////////////////////////

    public School getSchool()
    {
        return school;
    }

    public void setSchool( School school )
    {
        this.school = school;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public Long getId()
    {
        return id;
    }

    public void setId( Long selectedSchoolId )
    {
        this.id = selectedSchoolId;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public String getAction()
    {
        return action;
    }

    public void setAction( String selectedSchoolAction )
    {
        this.action = selectedSchoolAction;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isRendered()
    {
        return rendered;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isReadOnly()
    {
        return readOnly;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public String persistSchool()
    {
        //handle pojo crud here

        this.rendered = false;

        return null;
    }


    public String selectSchool()
    {
        if ( id == null )
        {
            this.school = new School();
        }
        else
        {
            this.school = this.schoolManager.get( id );
        }    
        
        this.readOnly = "Remove".equals( this.action );
        
        this.rendered = true;
        
        return null;
    }    

}
