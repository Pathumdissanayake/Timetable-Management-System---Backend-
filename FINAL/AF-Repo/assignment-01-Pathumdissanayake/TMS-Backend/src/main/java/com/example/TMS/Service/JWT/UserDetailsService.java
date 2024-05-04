package com.example.TMS.Service.JWT;

import com.example.TMS.Repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserInterface userInterface;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInterface.findByEmail(username).orElseThrow();
    }
}
