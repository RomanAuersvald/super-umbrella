package com.reported.sparest.controller;

import com.reported.sparest.dao.ProjectRepository;
import com.reported.sparest.dao.ProjectTaskRepository;
import com.reported.sparest.model.Project;
import com.reported.sparest.model.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@RestController("/tasks")
public class ProjectTaskRESTController {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // get All
    @CrossOrigin
    @GetMapping(path= "/all/{projectId}")
    public ResponseEntity<Collection> getAllInvoices(@PathVariable String projectId) {
        Collection<ProjectTask> tasks = projectTaskRepository.findProjectTasksByProjectId(projectId);
        return ResponseEntity.ok().body(tasks);
    }

    //get by ID
    @CrossOrigin
    @GetMapping("/{id}")
    ResponseEntity<ProjectTask> oneInvoice(@PathVariable String id) {
        Optional<ProjectTask> loadedTask = projectTaskRepository.findById(id);
        if (loadedTask.get() != null) {
            return ResponseEntity.ok().body(loadedTask.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PostMapping("/complete/{id}")
    ResponseEntity<ProjectTask> completeTask(@PathVariable String id) {
        Optional<ProjectTask> loadedTask = projectTaskRepository.findById(id);
        if (loadedTask.get() != null) {
            loadedTask.get().setEndDate(LocalDateTime.now());
            projectTaskRepository.save(loadedTask.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PostMapping("/startTaskEntry/{id}")
    ResponseEntity<ProjectTask> startTaskEntry(@PathVariable String id) {
        Optional<ProjectTask> loadedTask = projectTaskRepository.findById(id);
        if (loadedTask.get() != null) {
            loadedTask.get().newTaskEntry(); // zahájení nové entry - no shit
            projectTaskRepository.save(loadedTask.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PostMapping("/endTaskEntry/{id}")
    ResponseEntity<ProjectTask> endTaskEntry(@PathVariable String id) {
        Optional<ProjectTask> loadedTask = projectTaskRepository.findById(id);
        if (loadedTask.get() != null) {
            loadedTask.get().endLastTaskEntry();
            projectTaskRepository.save(loadedTask.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private Authentication getLoggeUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
