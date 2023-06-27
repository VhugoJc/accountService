package com.hyperskill.accountservice.repositories;

import com.hyperskill.accountservice.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogRepository extends JpaRepository<Log, Long> {
}
