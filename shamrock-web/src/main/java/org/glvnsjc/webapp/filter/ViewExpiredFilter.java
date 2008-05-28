package org.glvnsjc.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Work around when browser trying restore a view after its session timed out
 * @author dtran
 *
 */
public class ViewExpiredFilter
    implements Filter
{

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
        throws IOException, ServletException
    {
        try
        {
            chain.doFilter( request, response );
        }
        catch ( ServletException se )
        {
            Throwable cause = se.getCause();
            
            if ( cause != null && cause instanceof javax.faces.application.ViewExpiredException )
            {
                String directTo = ( (HttpServletRequest) request ).getContextPath();

                ( (HttpServletResponse) response ).sendRedirect( directTo );
            }
            else
            {
                throw se;
            }
        }
    }

    @Override
    public void init( FilterConfig filterConfig )
        throws ServletException
    {
        // TODO Auto-generated method stub

    }

}
