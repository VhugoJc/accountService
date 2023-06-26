package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.dtos.PaymentDTO;
import com.hyperskill.accountservice.models.Payment;
import com.hyperskill.accountservice.responses.StatusResponse;
import com.hyperskill.accountservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/acc")
public class AccountController {
    @Autowired
    PaymentService paymentService;

    // Add multiple payments.
    @PostMapping("/payment")
    public ResponseEntity<?> addPayments(@Validated @RequestBody List<PaymentDTO> payments){
        // Convert PaymentDTO objects to Payment entities using the paymentService.
        List<Payment> paymentsEntity = payments.stream()
                .map(payment -> paymentService.convertDtoPaymentToEntity(payment))
                .collect(Collectors.toList());
        StatusResponse response = this.paymentService.addPayments(paymentsEntity);
        return new ResponseEntity<StatusResponse>(response,HttpStatus.OK);
    }

    // Update a payment.
    @PutMapping("/payment")
    public ResponseEntity<?> updatePayment(@Validated @RequestBody PaymentDTO paymentDTO){
        // Convert PaymentDTO object to Payment entity using the paymentService.
        StatusResponse response = this.paymentService.updatePayment(this.paymentService.convertDtoPaymentToEntity(paymentDTO));
        return new ResponseEntity<StatusResponse>(response,HttpStatus.OK);
    }

}
