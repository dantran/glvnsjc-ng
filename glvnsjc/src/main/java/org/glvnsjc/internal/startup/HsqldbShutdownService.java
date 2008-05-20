package org.glvnsjc.internal.startup;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Allow HSQLDB to shutdown properly
 * @author dtran
 *
 */
@Component
public class HsqldbShutdownService
{

    private Log log = LogFactory.getLog( HsqldbShutdownService.class );

    @Resource
    private DataSource dataSource;


    @PreDestroy
    public void destroy()
    {
        try
        {
            BasicDataSource ds = ( BasicDataSource ) this.dataSource;
            
            if ( "org.hsqldb.jdbcDriver".equals( ds.getDriverClassName() ) )
            {
                log.info( "Shutting down embedded HSQLDB engine." );

                Connection conn = dataSource.getConnection();
                conn.createStatement().execute( "SHUTDOWN" );
                conn.close();

            }
        }
        catch ( SQLException e )
        {

        }

    }

}
