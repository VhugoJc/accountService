package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.logs.Event;
import com.hyperskill.accountservice.models.Log;
import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.repositories.ILogRepository;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogService implements ILogService{
    @Autowired
    ILogRepository logRepository;

    @Override
    public void addLog(Event eventName, String subject, String object, String path) {
        Log newLog = new Log();
        newLog.setAction(eventName.toString());
        newLog.setSubject(subject);
        newLog.setObject(object);
        newLog.setPath(path);
        this.logRepository.save(newLog);
    }

    @Override
    public List<Log> getAllLogs() {
        return this.logRepository.findAll();
    }


}
