package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.logs.Event;
import com.hyperskill.accountservice.models.User;
import jakarta.servlet.http.HttpServlet;

public interface ILogService {
    public void addLogSignUp(Event eventName, User user, HttpServlet request);
}
