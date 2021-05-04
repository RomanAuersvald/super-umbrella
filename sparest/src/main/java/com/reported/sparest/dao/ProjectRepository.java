package com.reported.sparest.dao;

import com.reported.sparest.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {

    @Override
    long count();

    Collection<Project> findProjectsByOwnerId(String id);
    Collection<Project> findProjectsByOwnerIdOrderByProjectStart(String id);

    Collection<Project> findProjectsByOwnerIdOrderByIdAsc(String id);


    Project findProjectById(String s);
}