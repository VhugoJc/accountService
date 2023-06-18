package com.hyperskill.accountservice.security;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userRepository.findByEmail(email);
        if (foundUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserPrincipal(foundUser);
    }
}

