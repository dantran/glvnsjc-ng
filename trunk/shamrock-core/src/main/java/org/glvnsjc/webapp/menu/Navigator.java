package org.glvnsjc.webapp.menu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class Navigator
{
    private String lastCompId = null;

    private List<NavigatorDescriptor> components = null;

    private NavigatorDescriptor currentComponent;

    private List<String> componentGroups = null;

    public boolean getHasCurrentComponent()
    {
        return currentComponent != null;
    }

    public NavigatorDescriptor getCurrentComponent()
    {
        String id = getComponentParam();
        if ( id != null )
        {
            setCurrentComponent( findComponentById( id ) );
            lastCompId = id;
        }
        else if ( lastCompId != null )
        {
            setCurrentComponent( findComponentById( lastCompId ) );
        }
        else
        {
            setCurrentComponent( findComponentById( "system" ) ); //fornow
            /*
            String uri = getComponentUri();
            setCurrentComponent( findComponentByUri( uri ) );
            */
        }
        return currentComponent;
    }

    private String getComponentUri()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        return ( (HttpServletRequest) fc.getExternalContext().getRequest() ).getRequestURI();
    }

    private String getComponentParam()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        String param = (String) fc.getExternalContext().getRequestParameterMap().get( "c" );
        if ( param != null && param.trim().length() > 0 )
        {
            return param;
        }
        else
        {
            return null;
        }
    }

    private List<NavigatorDescriptor> components_()
    {
        if ( components == null )
        {
            loadComponents();
        }
        return components;
    }

    public NavigatorDescriptor findComponentByUri( String uri )
    {
        Iterator<NavigatorDescriptor> it = components_().iterator();
        while ( it.hasNext() )
        {
            NavigatorDescriptor component = (NavigatorDescriptor) it.next();
            if ( uri.endsWith( component.getDemoLocation() ) )
            {
                return component;
            }
        }
        return null;
    }

    public NavigatorDescriptor findComponentById( String id )
    {
        Iterator<NavigatorDescriptor> it = components_().iterator();
        while ( it.hasNext() )
        {
            NavigatorDescriptor component = (NavigatorDescriptor) it.next();
            if ( component.getId().equals( id ) )
            {
                return component;
            }
        }
        return null;
    }

    public void setCurrentComponent( NavigatorDescriptor currentComponent )
    {
        if ( currentComponent == null )
        {
            this.currentComponent = (NavigatorDescriptor) components_().get( 0 );
        }
        this.currentComponent = currentComponent;
    }

    public List<String> getComponentGroups()
    {
        return componentGroups;
    }

    public void setComponentGroups( List<String> componentGroups )
    {
        this.componentGroups = componentGroups;
    }

    private List<NavigatorDescriptor> getFilteredComponents( String group )
    {
        List<NavigatorDescriptor> ret = new ArrayList<NavigatorDescriptor>();
        Iterator<NavigatorDescriptor> it = getComponents().iterator();
        while ( it.hasNext() )
        {
            NavigatorDescriptor desc = (NavigatorDescriptor) it.next();
            if ( desc.getGroup().equals( group ) )
            {
                ret.add( desc );
            }
        }
        return ret;
    }

    public List<NavigatorDescriptor> getSystemMenu()
    {
        return getFilteredComponents( "system" );
    }

    public List<NavigatorDescriptor> getSchoolMenu()
    {
        return getFilteredComponents( "school" );
    }

    public List<NavigatorDescriptor> getInstructorMenu()
    {
        return getFilteredComponents( "instructor" );
    }

    public List<NavigatorDescriptor> getStudentMenu()
    {
        return getFilteredComponents( "student" );
    }

    public List<NavigatorDescriptor> getSelectComponents()
    {
        return getFilteredComponents( "richSelect" );
    }


    public List<NavigatorDescriptor> getComponents()
    {
        Iterator<NavigatorDescriptor> it = components_().iterator();
        NavigatorDescriptor cur = getCurrentComponent();
        while ( it.hasNext() )
        {
            NavigatorDescriptor desc = (NavigatorDescriptor) it.next();
            if ( desc.equals( cur ) )
            {
                desc.setCurrent( true );
            }
            else
            {
                desc.setCurrent( false );
            }
        }
        return components;
    }

    public void setComponents( List<NavigatorDescriptor> components )
    {
        this.components = components;
    }

    private void loadComponents()
    {
        Properties props = new Properties();
        List<NavigatorDescriptor> temp = new ArrayList<NavigatorDescriptor>();
        InputStream is = null;
        try
        {
            is = this.getClass().getResourceAsStream( "/org/glvnsjc/webapp/menu/menu.properties" );
            props.load( is );
        }
        catch ( Exception e )
        {
            throw new FacesException( e );
        }
        finally
        {
            IOUtils.closeQuietly( is );
        }
        
        Set entries = props.entrySet();
        Iterator it = entries.iterator();
        while ( it.hasNext() )
        {
            Map.Entry e = (Map.Entry) it.next();
            NavigatorDescriptor desc = new NavigatorDescriptor();
            desc.setId( e.getKey().toString().trim() );
            StringTokenizer toc = new StringTokenizer( e.getValue().toString(), "," );
            //			#id=name,captionImage,iconImage,devGuideLocation,tldDocLocation,javaDocLocation
            desc.setGroup( toc.nextToken().trim() );
            desc.setName( toc.nextToken().trim() );
            desc.setIconImage( toc.nextToken().trim() );
            desc.setCaptionImage( toc.nextToken().trim() );
            desc.setDevGuideLocation( toc.nextToken().trim() );
            desc.setTldDocLocation( toc.nextToken().trim() );
            desc.setJavaDocLocation( toc.nextToken().trim() );
            desc.setDemoLocation( toc.nextToken().trim() );
            temp.add( desc );
        }
        
        Collections.sort( temp, new Comparator()
        {
            public int compare( Object o1, Object o2 )
            {
                NavigatorDescriptor d1 = (NavigatorDescriptor) o1;
                NavigatorDescriptor d2 = (NavigatorDescriptor) o2;
                return d1.getName().compareTo( d2.getName() );
            }
        } );
        
        setComponents( temp );
        setCurrentComponent( (NavigatorDescriptor) temp.get( 0 ) );
    }
}
