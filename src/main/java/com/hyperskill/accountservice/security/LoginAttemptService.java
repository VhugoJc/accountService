package com.hyperskill.accountservice.security;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/*
 The LoginAttemptService class handles login attempts and user lockout functionality.
 It provides methods to track failed login attempts and reset login attempts for a user.
 */
@Service
public class LoginAttemptService {
    public static final int MAX_ATTEMPT = 5;
    @Autowired
    UserService userService;

    // Track a failed login attempt for a user.
    public void loginFailed(String username){
        User user = this.userService.getUserByEmail(username);
        if(user!=null && !user.isLocked() && !user.getRoles().contains("ROLE_ADMINISTRATOR")){ ///if the user exists, is not locked and is not an admin
            if(user.getFailedAttempt()+1>=MAX_ATTEMPT){
                user.setFailedAttempt(5);
                user.setLocked(true);
            }else{
                user.setFailedAttempt(user.getFailedAttempt()+1);
            }
            this.userService.updateUSer(user);
        }
    }

    // Reset login attempts for a user.
    public void ResetAttempts(String username){
        User user = this.userService.getUserByEmail(username);
        if(user!=null){
            user.setFailedAttempt(0);
            user.setLocked(false);
            this.userService.updateUSer(user);
        }
    }


}
