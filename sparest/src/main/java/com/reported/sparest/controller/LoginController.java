package com.reported.sparest.controller;

import com.reported.sparest.auth.exception.FailedToLoginException;
import com.reported.sparest.auth.model.AuthenticationResponse;
import com.reported.sparest.auth.model.UserCredentials;
import com.reported.sparest.auth.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserAuthenticationService authenticationService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationResponse userLogin(@RequestBody UserCredentials userCredentials) throws FailedToLoginException {

        if (userCredentials == null || (userCredentials.getUsername() == null || userCredentials.getPassword() == null)) {
            throw new FailedToLoginException("Missing login credentials ");
        }

        String token = authenticationService.authenticateUser(userCredentials.getUsername(), userCredentials.getPassword());

        if (token != null) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setUsername(userCredentials.getUsername());
            authenticationResponse.setToken(token);
            return authenticationResponse;
        }
        throw new FailedToLoginException(String.format(" unable to authenticate user [%s] ", userCredentials.getUsername()));
    }
}
