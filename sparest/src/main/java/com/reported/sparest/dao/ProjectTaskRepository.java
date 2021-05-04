package com.reported.sparest.dao;


import com.reported.sparest.model.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, String> {

    Collection<ProjectTask> findProjectTasksByProjectId(String id);

    Collection<ProjectTask> findProjectTaskByProjectIdOrderByStartDateAsc(String id);

    Collection<ProjectTask> findProjectTasksByProjectIdOrderByStartDateAsc(String id);
}
