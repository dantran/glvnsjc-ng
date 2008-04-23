package org.glvnsjc;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import junit.framework.TestCase;


public abstract class BaseTestCase
    extends TestCase
{
    protected final Log log = LogFactory.getLog( getClass() );

    protected File basedir = new File( System.getProperty( "basedir", "." ) );

    protected File targetDir = new File( basedir, "target");

}
