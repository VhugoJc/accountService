package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public User addUser(User newUser){
        this.emailValidation(newUser.getEmail());
        this.passwordValidation(newUser.getPassword());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> user = this.userRepository.findAllUsersByEmail(email);
        return user.get(0);
    }

    public void passwordValidation (String password){
        String regrex = ".*password.*";
        if(password.length()<12){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The password length must be at least 12 chars!");
        }
        if(password.toLowerCase().matches(regrex)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The password is in the hacker's database!");
        }

    }

    public void emailValidation (String email){

        List<User> userList = userRepository.findAllUsersByEmail(email);
        String domain = "@acme.com";
        if(userList.size()>0){ //if the user exists or does not match with the domain
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "user exist!");
        }
        if(!email.contains(domain)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"wrong domain");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findUserByEmail(email);
        if (opt.isEmpty())
            throw new UsernameNotFoundException("User with email: " + email + " not found !");
        else {
            User user = opt.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toSet())
            );
        }
    }
}
