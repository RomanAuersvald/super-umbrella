package com.reported.sparest.dao;

import com.reported.sparest.model.ReportedUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<ReportedUser, String> {

    public ReportedUser findByFirstName(String firstName);
    public List<ReportedUser> findByLastName(String lastName);

    // použito pro vyhledání uživatele podle uživ jména v přihlášení
    // ok then
    public ReportedUser findByUsername(String login);
}