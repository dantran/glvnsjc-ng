package org.glvnsjc.internal.dao;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.test.jpa.AbstractJpaTests;

/**
 * Base class for running DAO tests.
 * @author mraible
 */
public abstract class BaseDaoTestCase
    extends AbstractJpaTests
{
    protected final Log log = LogFactory.getLog( getClass() );

    protected File basedir = new File( System.getProperty( "basedir", "." ) );

    protected File targetDir = new File( basedir, "target");

    protected ResourceBundle rb;

    protected String[] getConfigPaths()
    {
        setAutowireMode( AUTOWIRE_BY_NAME );
            return new String[] {
            "/org/glvnsjc/test/applicationContext-resources.xml",
            "/org/glvnsjc/applicationContext-datasource.xml",
            "/org/glvnsjc/applicationContext-persistence.xml",
            "/org/glvnsjc/applicationContext-jpa.xml",
            "/org/glvnsjc/applicationContext-dao.xml"
            };
    }

    public BaseDaoTestCase()
    {
        // Since a ResourceBundle is not required for each class, just
        // do a simple check to see if one exists
        String className = this.getClass().getName();

        try
        {
            rb = ResourceBundle.getBundle( className );
        }
        catch ( MissingResourceException mre )
        {
            //log.warn("No resource bundle found for: " + className);
        }
    }

    /**
     * Utility method to populate a javabean-style object with values
     * from a Properties file
     * @param obj the model object to populate
     * @return Object populated object
     * @throws Exception if BeanUtils fails to copy properly
     */
    protected Object populate( Object obj )
        throws Exception
    {
        // loop through all the beans methods and set its properties from
        // its .properties file
        Map<String, String> map = new HashMap<String, String>();

        for ( Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements(); )
        {
            String key = keys.nextElement();
            map.put( key, rb.getString( key ) );
        }

        BeanUtils.copyProperties( map, obj );

        return obj;
    }
}
