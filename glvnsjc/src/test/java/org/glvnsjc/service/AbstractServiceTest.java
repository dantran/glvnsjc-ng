package org.glvnsjc.service;

import java.io.File;

import org.apache.myfaces.orchestra.frameworkAdapter.FrameworkAdapter;
import org.apache.myfaces.orchestra.frameworkAdapter.local.LocalFrameworkAdapter;
import org.dom4j.rule.RuleManager;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * @author dtran
 */
public abstract class AbstractServiceTest
    extends AbstractDependencyInjectionSpringContextTests
{
    protected File basedir = new File( System.getProperty( "basedir", "." ) );
    protected File targetDir = new File( basedir, "target");
    
    protected LocalFrameworkAdapter localFrameworkAdapter;


    public void setLocalFrameworkAdapter( LocalFrameworkAdapter localFrameworkAdapter )
    {
        this.localFrameworkAdapter = localFrameworkAdapter;
    }

    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////

    protected String[] getConfigPaths()
    {
        setAutowireMode( AUTOWIRE_BY_NAME );
            return new String[] {
            "/org/glvnsjc/test/applicationContext-resources.xml",
            "/org/glvnsjc/applicationContext-datasource.xml",
            "/org/glvnsjc/applicationContext-persistence.xml",
            "/org/glvnsjc/applicationContext-jpa.xml",
            "/org/glvnsjc/applicationContext-dao.xml",
            "/org/glvnsjc/applicationContext-orchestra.xml",
            "/org/glvnsjc/applicationContext-service.xml"
            };
    }
    

    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////

    protected void onSetUp()
        throws Exception
    {
        super.onSetUp();

        FrameworkAdapter.setCurrentInstance( this.localFrameworkAdapter );
    }



}
