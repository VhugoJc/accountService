package com.hyperskill.accountservice.repositories;

import com.hyperskill.accountservice.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByEmployee(String employee);
}
