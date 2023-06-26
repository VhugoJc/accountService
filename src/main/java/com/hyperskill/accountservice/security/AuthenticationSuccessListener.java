package com.hyperskill.accountservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/*
 The AuthenticationSuccessListener class is responsible for handling authentication success events.
 It listens for AuthenticationSuccessEvent and performs the necessary actions.
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    LoginAttemptService loginAttemptService;

    // Handle the authentication success event.
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        loginAttemptService.ResetAttempts(event.getAuthentication().getName());
    }
}
