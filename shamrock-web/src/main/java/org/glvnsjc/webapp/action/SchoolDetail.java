package org.glvnsjc.webapp.action;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.SchoolManager;
import org.glvnsjc.webapp.util.RequestUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
//@ConversationName("schoolDetail")
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

    @PostConstruct
    public void init()
    {
        String id = RequestUtil.getRequestParam( "id" );
        Object action = RequestUtil.getRequestParam( "action" );

        if ( id != null )
        {
            this.setId( Long.parseLong( id.toString() ) );
        }

        if ( action != null )
        {
            this.setAction( action.toString() );
        }

        selectSchool();
    }

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

        if ( this.action != null )
        {
            this.rendered = true;
        }

        return null;
    }

    public void setSchoolManager( SchoolManager schoolManager )
    {
        this.schoolManager = schoolManager;
    }

}
