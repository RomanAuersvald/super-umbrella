package com.reported.logging.dao;

import com.reported.logging.model.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LogRepository extends CrudRepository<Log, String> {

    Collection<Log> findAllByUserIdOrderByIdDesc(String s);
}
