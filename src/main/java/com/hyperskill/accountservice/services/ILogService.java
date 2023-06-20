package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.logs.Event;
import com.hyperskill.accountservice.models.Log;
import com.hyperskill.accountservice.models.User;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

public interface ILogService {
    public void addLog(Event eventName, String subject, String object, String path);
    public List<Log> getAllLogs();
}
