package com.hyperskill.accountservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Data
public class Log {
    @Id
    @GeneratedValue
    @Column(name = "log_id")
    private int id;
    @Column(name = "log_date")
    private String date;
    @Column(name = "log_action")
    private String action;
    @Column(name = "log_subject")
    private String subject;
    @Column(name = "log_object")
    private String object;
    @Column(name = "log_path")
    private String path;

    public Log() {
        this.date = LocalDateTime.now().toString();
    }
}
