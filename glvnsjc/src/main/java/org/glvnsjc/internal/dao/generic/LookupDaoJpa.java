package org.glvnsjc.internal.dao.generic;

import java.util.List;

import org.glvnsjc.model.domain.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of LookupDao.
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 */
@Repository
@Transactional
public class LookupDaoJpa
    extends UniversalDaoJpa
    implements LookupDao
{

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoles()
    {
        log.debug( "Retrieving all role names..." );

        return super.entityManager.createQuery( "select r from Role r order by name" ).getResultList();
    }
}
