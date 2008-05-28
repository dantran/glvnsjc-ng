package org.glvnsjc.internal.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.glvnsjc.internal.dao.SchoolDao;
import org.glvnsjc.model.domain.School;
import org.springframework.stereotype.Repository;

@Repository( "schoolDao")
public class SchoolDaoJpa
    extends GenericNameDaoJpa<School, Long>
    implements SchoolDao
{
    public SchoolDaoJpa()
    {
        super( School.class );
    }
    
    @SuppressWarnings("unchecked")
    public List<School> getMainSchools()
    {
        String queryStr = "select school from School school where school.parentSchool is null";
        
        Query query = this.entityManager.createQuery( queryStr );
        
        return query.getResultList();
    }
}
