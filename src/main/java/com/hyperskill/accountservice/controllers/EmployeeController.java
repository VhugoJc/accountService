package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.responses.EmpPaymentResponse;
import com.hyperskill.accountservice.responses.PaymentResponse;
import com.hyperskill.accountservice.services.PaymentService;
import com.hyperskill.accountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/empl")
public class EmployeeController implements IEmployeeController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;

    //GET api/empl/payment
    @GetMapping("/payment")
    public ResponseEntity<?> getPayrool(@RequestParam(required = false) String period,  Principal principal) {
        User employee = this.userService.getUserByEmail(principal.getName());

        if (period != null) {
            EmpPaymentResponse paymentEmpl = this.paymentService.getPaymentByPeriod(period, employee);
            return new ResponseEntity<EmpPaymentResponse>(paymentEmpl, HttpStatus.OK);
        }
        List<EmpPaymentResponse> paymenmtEmpList = this.paymentService.getAllPayments(employee);

        return new ResponseEntity<List<EmpPaymentResponse>>(paymenmtEmpList,HttpStatus.OK);
    }
}
