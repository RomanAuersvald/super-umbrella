package com.reported.logging.controller;


import com.reported.logging.dao.LogRepository;
import com.reported.logging.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping(path = "/logging")
@RestController
public class LogRESTController {

    @Autowired
    private LogRepository logRepository;

    @PostMapping("")
    public ResponseEntity addLogEntry(@RequestBody Log newLogEntry){
        logRepository.save(newLogEntry);
        System.out.println("Log: " + newLogEntry.id + ", user: " + newLogEntry.getUserId() + " , type: " + newLogEntry.getType() + " , notification: " + newLogEntry.getNotification() + " , date: " + newLogEntry.getTimestamp());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Collection> getAllEntries(@PathVariable String userId){
        Collection<Log> userLogs = logRepository.findAllByUserIdOrderByIdDesc(userId);
        return ResponseEntity.ok().body(userLogs);
    }

}
