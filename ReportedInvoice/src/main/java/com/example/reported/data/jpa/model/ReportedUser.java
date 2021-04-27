package com.example.reported.data.jpa.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Validated
@Document(collection = "users")
public class ReportedUser{


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    private String id;

    @NotEmpty(message="Name cannot be empty")
    private String firstName;
    @NotEmpty(message="Last name cannot be empty")
    private String lastName;
    @NotEmpty(message="username cannot be empty")
    private String username;
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;
    private String role;

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    private String bankAccount;

    public ReportedUser() {}

    public ReportedUser(String username, String password, String role, String name, String surname){
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = name;
        this.lastName = surname;
    }

    @Override
    public String toString() {
        return String.format(
                "User[firstName='%s', lastName='%s', role='%s', username='%s']",
                firstName, lastName, role, username);
    }

    public String getNiceNameAndLastname(){
        return String.format(
                "%s %s",
                firstName, lastName);
    }


    public List<String> getRoleList(){
        if(this.role.length() > 0) {
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
