package org.glvnsjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.glvnsjc.internal.dao.SchoolDao;
import org.glvnsjc.model.domain.School;
import org.glvnsjc.service.SchoolManager;
import org.springframework.stereotype.Service;

@Service( "schoolManager")
public class SchoolManagerImpl
    extends GenericNameManagerImpl<School, Long>
    implements SchoolManager
{

    private SchoolDao schoolDao;
    
    public SchoolManagerImpl()
    {
        super( null );
    }
    
    public List<School> getMainSchools()
    {
        return this.schoolDao.getMainSchools();
    }

    @Resource
    public void setSchoolDao( SchoolDao schoolDao )
    {
        super.genericDao = schoolDao;
        this.schoolDao = schoolDao;
    }
}
