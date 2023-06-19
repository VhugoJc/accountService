package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.responses.ChangePassResponse;
import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.repositories.IUserRepository;
import com.hyperskill.accountservice.responses.StatusUserResponse;
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

    private void passwordValidation (String password){
        String regrex = ".*password.*";
        if(password.length()<12){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The password length must be at least 12 chars!");
        }
        if(password.toLowerCase().matches(regrex)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The password is in the hacker's database!");
        }

    }

    private void emailValidation (String email){

        List<User> userList = userRepository.findAllUsersByEmail(email);
        String domain = "@acme.com";
        if(userList.size()>0){ //if the user exists or does not match with the domain
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "user exist!");
        }
        if(!email.contains(domain)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"wrong domain");
        }
    }

    public ChangePassResponse changePass(String email, String password){
        List<User> userStored = this.userRepository.findAllUsersByEmail(email);

        this.passwordValidation(password);//validate password security
        System.out.println(userStored.get(0).getPassword());

        if(passwordEncoder.matches(password, userStored.get(0).getPassword())){ //validate Old
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The passwords must be different!");
        }
        userStored.get(0).setPassword(passwordEncoder.encode(password));
        this.userRepository.save(userStored.get(0));

        ChangePassResponse response = new ChangePassResponse();
        response.setEmail(email);
        response.setStatus("The password has been updated successfully");
        return response;
    }

    @Override
    public boolean userExist(String email) {
        List<User> userList = userRepository.findAllUsersByEmail(email);
        if(userList.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public StatusUserResponse deleteUser(String email) {
        if(!userExist(email)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found!");
        }
        User userToDelete = this.getUserByEmail(email);
        if(userToDelete.getRoles().contains("ROLE_ADMINISTRATOR")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't remove ADMINISTRATOR role!");
        }
        this.userRepository.delete(userToDelete);
        return new StatusUserResponse(email,"Deleted successfully!");
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
