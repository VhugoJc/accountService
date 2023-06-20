package com.hyperskill.accountservice.repositories;

import com.hyperskill.accountservice.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogRepository extends JpaRepository<Log, Long> {
}
