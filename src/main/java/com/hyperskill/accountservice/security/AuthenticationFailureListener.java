package com.hyperskill.accountservice.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
/*
 The AuthenticationFailureListener class is responsible for handling authentication failure events.
 It listens for AuthenticationFailureBadCredentialsEvent and performs the necessary actions.
 */

@Component
public class AuthenticationFailureListener implements
        ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Autowired LoginAttemptService loginAttemptService;

    // Handle the authentication failure event.
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        loginAttemptService.loginFailed(event.getAuthentication().getName());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, event.getException().getMessage());
    }
}
