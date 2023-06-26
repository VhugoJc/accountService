package com.hyperskill.accountservice.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;

/*
 The RestAuthenticationEntryPoint class is responsible for handling authentication entry point scenarios in RESTful endpoints.
 It implements the AuthenticationEntryPoint interface to provide custom handling logic.
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // Handle the authentication entry point scenario.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}