package com.hyperskill.accountservice.security;

import com.hyperskill.accountservice.exceptions.ErrorMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;


import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Date;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        if(response.getStatus()==401){
            ErrorMessage.setErrorMsg(authException.getMessage());
        }
        response.getOutputStream().println("{\n" +
                    "\"timestamp\": \"" +  new Date().toString() + "\"\n" +
                "    \"status\": " + response.getStatus() + "\n" +
                "    \"error\": \"" + HttpStatus.valueOf(response.getStatus()).getReasonPhrase() + "\"\n" +
                "    \"message\": \"" + ErrorMessage.getErrorMsg() + "\"\n" +
                "    \"path\": \"" + request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) + "\"\n" +
                " }");

    }

}