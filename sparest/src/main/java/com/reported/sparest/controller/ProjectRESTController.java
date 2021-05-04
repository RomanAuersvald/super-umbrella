package com.reported.sparest.controller;

import com.reported.sparest.dao.ProjectRepository;
import com.reported.sparest.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectRESTController {

    @Autowired
    private ProjectRepository projectRepository;


    // get All
    @CrossOrigin
    @GetMapping(path= "/all/{userID}")
    public ResponseEntity<Collection> getAllInvoices(@PathVariable String userID) {
        Collection<Project> projects = projectRepository.findProjectsByOwnerId(userID);
        return ResponseEntity.ok().body(projects);
    }

    //get by ID
    @CrossOrigin
    @GetMapping("/{id}")
    ResponseEntity<Project> oneInvoice(@PathVariable String id) {
        Optional<Project> loadedProject = projectRepository.findById(id);
        if (loadedProject.get() != null) {
            return ResponseEntity.ok().body(loadedProject.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private Authentication getLoggeUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
