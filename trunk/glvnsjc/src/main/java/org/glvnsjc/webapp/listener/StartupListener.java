package org.glvnsjc.webapp.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.orchestra.frameworkAdapter.FrameworkAdapter;
import org.apache.myfaces.orchestra.frameworkAdapter.local.LocalFrameworkAdapter;
import org.glvnsjc.Constants;
import org.glvnsjc.service.NameExistsException;
import org.glvnsjc.service.PredefinedRoles;
import org.glvnsjc.service.demo.CreateDemoData;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.ProviderManager;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.providers.rememberme.RememberMeAuthenticationProvider;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <p>StartupListener class used to initialize and database settings
 * and populate any application-wide drop-downs.
 * <p/>
 * <p>Keep in mind that this listener is executed outside of OpenSessionInViewFilter,
 * so if you're using Hibernate you'll have to explicitly initialize all loaded data at the
 * GenericDao or service level to avoid LazyInitializationException. Hibernate.initialize() works
 * well for doing this.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StartupListener
    implements ServletContextListener
{
    private static final Log log = LogFactory.getLog( StartupListener.class );

    @SuppressWarnings( { "unchecked" })
    public void contextInitialized( ServletContextEvent event )
    {
        log.debug( "Initializing context..." );

        ServletContext context = event.getServletContext();

        // Orion starts Servlets before Listeners, so check if the config
        // object already exists
        Map<String, Object> config = (HashMap<String, Object>) context.getAttribute( Constants.CONFIG );

        if ( config == null )
        {
            config = new HashMap<String, Object>();
        }

        if ( context.getInitParameter( Constants.CSS_THEME ) != null )
        {
            config.put( Constants.CSS_THEME, context.getInitParameter( Constants.CSS_THEME ) );
        }

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext( context );

        PasswordEncoder passwordEncoder = null;
        try
        {
            ProviderManager provider = (ProviderManager) ctx.getBean( "_authenticationManager" );
            for ( Object o : provider.getProviders() )
            {
                AuthenticationProvider p = (AuthenticationProvider) o;
                if ( p instanceof RememberMeAuthenticationProvider )
                {
                    config.put( "rememberMeEnabled", Boolean.TRUE );
                }
                else if ( ctx.getBean( "passwordEncoder" ) != null )
                {
                    passwordEncoder = (PasswordEncoder) ctx.getBean( "passwordEncoder" );
                }
            }
        }
        catch ( NoSuchBeanDefinitionException n )
        {
            log.debug( "authenticationManager bean not found, assuming test and ignoring..." );
            // ignore, should only happen when testing
        }

        context.setAttribute( Constants.CONFIG, config );

        // output the retrieved values for the Init and Context Parameters
        if ( log.isDebugEnabled() )
        {
            log.debug( "Remember Me Enabled? " + config.get( "rememberMeEnabled" ) );
            if ( passwordEncoder != null )
            {
                log.debug( "Password Encoder: " + passwordEncoder.getClass().getName() );
            }
            log.debug( "Populating drop-downs..." );
        }

        setupContext( event.getServletContext() );
    }

    /**
     * This method uses the LookupManager to lookup available roles from the data layer.
     * @param context The servlet context
     */
    public static void setupContext( ServletContext context )

    {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext( context );
        PredefinedRoles mgr = (PredefinedRoles) ctx.getBean( "predefinedRoles" );

        // get list of possible roles
        context.setAttribute( Constants.AVAILABLE_ROLES, mgr.getAllRolesWithLabels() );
        log.debug( "Drop-down initialization complete [OK]" );

        //load UserInit bean
        ctx.getBean( "userInit" );

        try
        {
            LocalFrameworkAdapter localFrameworkAdapter = (LocalFrameworkAdapter) ctx.getBean( "localFrameworkAdapter" );
            FrameworkAdapter frameworkAdapter = FrameworkAdapter.getCurrentInstance();
            FrameworkAdapter.setCurrentInstance( localFrameworkAdapter );
            CreateDemoData demoData = (CreateDemoData) ctx.getBean( "createDemoData" );
            demoData.init();
            FrameworkAdapter.setCurrentInstance( frameworkAdapter );
        }
        catch ( NameExistsException e )
        {
            throw new IllegalStateException( e );
        }

    }

    /**
     * Shutdown servlet context (currently a no-op method).
     *
     * @param servletContextEvent The servlet context event
     */
    public void contextDestroyed( ServletContextEvent servletContextEvent )
    {
        //LogFactory.release(Thread.currentThread().getContextClassLoader());
        //Commented out the above call to avoid warning when SLF4J in classpath.
        //WARN: The method class org.apache.commons.logging.impl.SLF4JLogFactory#release() was invoked.
        //WARN: Please see http://www.slf4j.org/codes.html for an explanation.
    }
}
