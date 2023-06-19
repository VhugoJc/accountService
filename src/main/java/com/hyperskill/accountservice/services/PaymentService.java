package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.dtos.PaymentDTO;
import com.hyperskill.accountservice.models.Payment;

import com.hyperskill.accountservice.repositories.IPaymentRepository;
import com.hyperskill.accountservice.responses.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class PaymentService implements IPaymentService{
    @Autowired
    IPaymentRepository paymentRepository;
    @Autowired
    UserService userService;
    public Payment convertDtoPaymentToEntity(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setEmployee(dto.getEmployee());
        payment.setSalary(dto.getSalary());
        try {
            Date date = new SimpleDateFormat("MM-yyyy").parse(dto.getPeriod());
            payment.setPeriod(date);
        } catch (ParseException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return payment;
    }

    private void periodPerEmployeeExist(String employee, Date period, int count){
        List<Payment> paymentsByEmployee = this.paymentRepository.findAllByEmployee(employee);
        for(Payment p: paymentsByEmployee){
            if(period.equals(p.getPeriod())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The period ["+count+"] must be unique for this employee");
            }
        }
    }


    @Override
    public StatusResponse addPayments(List<Payment> payments) {
        int count = 0;
        //validation:
        for (Payment p : payments) {
            //employee validation
            if(!this.userService.userExist(p.getEmployee())){ //if employee does not exist
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"An employee ["+count+"] must be among the users of our service!");
            }
            //period validation
            this.periodPerEmployeeExist(p.getEmployee(),p.getPeriod(), count);
            //salary validation
            if(p.getSalary()<0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Salary  ["+count+"]cannot be negative");
            }
            count++;
        }
        //save
        for(Payment p: payments){
            this.paymentRepository.save(p);
        }
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setStatus("Updated successfully!");
        return statusResponse ;
    }
}
