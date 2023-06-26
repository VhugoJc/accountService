package com.hyperskill.accountservice.security;

import com.hyperskill.accountservice.logs.Event;
import com.hyperskill.accountservice.services.LogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
/*
 The RestAccessDeniedHandler class is responsible for handling access denied scenarios in RESTful endpoints.
 It implements the AccessDeniedHandler interface to provide custom handling logic.
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    LogService logService;

    // Handle the access denied scenario.
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logService.addLog(Event.ACCESS_DENIED, request.getUserPrincipal().getName(),request.getRequestURI(),request.getRequestURI());
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied!");
    }
}
