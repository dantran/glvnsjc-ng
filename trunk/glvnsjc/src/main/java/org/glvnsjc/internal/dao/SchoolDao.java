package org.glvnsjc.internal.dao;

import java.util.List;

import org.glvnsjc.model.domain.School;

public interface SchoolDao
    extends GenericNameDao<School, Long>
{
    List<School> getMainSchools();
}
