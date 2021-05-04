package com.reported.sparest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Document(collection = "projects")
public class Project {

    public Project() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    private String id;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setProjectStart(LocalDateTime projectStart) {
        this.projectStart = projectStart;
    }

    @NotEmpty(message = "Project name cannot be empty")
    private  String name;
    @NotEmpty(message = "Project description cannot be empty")
    private  String description;
    // tiny mce rich boi
    private String longDescription;
    private  String ownerId;
    @NotNull(message = "Fill estimated budget it will be cool")
    private Double estimatedHours;

    private Double hourRate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime projectStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime projectEnd;

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Double getHourRate() {
        return hourRate;
    }

    public void setHourRate(Double hourRate) {
        this.hourRate = hourRate;
    }

    public Double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public LocalDateTime getProjectStart() {
        return projectStart;
    }

    public LocalDateTime getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(LocalDateTime projectEnd) {
        this.projectEnd = projectEnd;
    }

    public Project(String name, String description, String ownerId, LocalDateTime projectStart){
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.projectStart = projectStart;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerId() {
        return ownerId;
    }



}