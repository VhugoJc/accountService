package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.models.Payment;
import com.hyperskill.accountservice.responses.StatusResponse;

import java.util.List;

public interface IPaymentService {
    public StatusResponse addPayments (List<Payment> payments);
}
