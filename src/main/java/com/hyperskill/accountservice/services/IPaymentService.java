package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.models.Payment;
import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.responses.EmpPaymentResponse;
import com.hyperskill.accountservice.responses.PaymentResponse;
import com.hyperskill.accountservice.responses.StatusResponse;

import java.util.List;

public interface IPaymentService {
    public StatusResponse addPayments (List<Payment> payments);
    public StatusResponse updatePayment(Payment paymentDTO);

    public EmpPaymentResponse getPaymentByPeriod(String period, User employee);
    public List<EmpPaymentResponse> getAllPayments(User employee);
}
