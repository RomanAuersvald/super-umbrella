package com.reported.sparest.service;

import com.reported.sparest.dao.UserRepository;
import com.reported.sparest.model.ReportedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportedUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ReportedUser> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if(optionalUser.isPresent()) {
            return new ReportedUser( optionalUser.get().getUsername(), optionalUser.get().getPassword(), optionalUser.get().getRole(), optionalUser.get().getFirstName(), optionalUser.get().getLastName());
        }
        return null;
    }
}