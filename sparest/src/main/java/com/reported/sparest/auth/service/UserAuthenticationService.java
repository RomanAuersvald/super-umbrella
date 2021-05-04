package com.reported.sparest.auth.service;

import com.reported.sparest.auth.exception.FailedToLoginException;
import com.reported.sparest.auth.exception.JwtAuthenticationException;
import com.reported.sparest.auth.model.User;
import com.reported.sparest.dao.UserRepository;
import com.reported.sparest.model.ReportedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Component
public class UserAuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticateUser(String username, String password) throws FailedToLoginException {
        boolean isAuthenticated = false;

        Optional<ReportedUser> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (optionalUser.isPresent()){
            //String encodedPassword = passwordEncoder.encode(password);
            //if (optionalUser.get().getPassword() == encodedPassword ){
                isAuthenticated = true;
            }else{
                isAuthenticated = false;
            }
        //}

        if (isAuthenticated) {
            try {
                return jwtService.generateToken(username);
            } catch (URISyntaxException | IOException e) {
                throw new FailedToLoginException(e.getMessage());
            }
        }
        throw new FailedToLoginException(String.format("unable to authenticate user [%s]", username));
    }

    public User authenticateToken(String jwtToken) throws JwtAuthenticationException {

        try {
            String username = jwtService.verifyToken(jwtToken);
            List<String> userRoles = userService.getUserRoles(username);

            User user = new User();
            user.setUsername(username);
            user.setUserRoles(userRoles);
            return user;
        } catch (IOException | URISyntaxException e) {
            throw new JwtAuthenticationException(e.getMessage(), e);
        }
    }
}
