package org.glvnsjc.service;

import java.util.List;

import org.glvnsjc.model.domain.School;

public interface SchoolManager
    extends GenericNameManager<School, Long>
{
    List<School> getMainSchools();
}
