package com.reported.sparest.auth.service;

import com.reported.sparest.dao.UserRepository;
import com.reported.sparest.model.ReportedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<String> getUserRoles(String username) {

        List<String> userRoles = new ArrayList<String>();

        Optional<ReportedUser> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (optionalUser.isPresent()){
            return Arrays.asList(optionalUser.get().getRole());
        }
        return userRoles;
    }
}
