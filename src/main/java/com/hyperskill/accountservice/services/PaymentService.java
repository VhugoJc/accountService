package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.dtos.PaymentDTO;
import com.hyperskill.accountservice.models.Payment;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.repositories.IPaymentRepository;
import com.hyperskill.accountservice.responses.EmpPaymentResponse;
import com.hyperskill.accountservice.responses.PaymentResponse;
import com.hyperskill.accountservice.responses.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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

    // Check if a payment period already exists for an employee.
    private void periodPerEmployeeExist(String employee, Date period, int count){
        List<Payment> paymentsByEmployee = this.paymentRepository.findAllByEmployee(employee);
        for(Payment p: paymentsByEmployee){
            if(period.equals(p.getPeriod())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The period ["+count+"] must be unique for this employee");
            }
        }
    }


    // Add multiple payments to the system.
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
        count = 0;
        for(Payment p: payments){
            this.periodPerEmployeeExist(p.getEmployee(),p.getPeriod(), count++);
            this.paymentRepository.save(p);
        }
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setStatus("Updated successfully!");
        return statusResponse ;
    }

    // Update an existing payment.
    @Override
        public StatusResponse updatePayment(Payment payment) {
        if(!this.userService.userExist(payment.getEmployee())){ //if employee does not exist
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the employee must be among the users of our service!");
        }
        List<Payment> paymentsEmployee = this.paymentRepository.findAllByEmployee(payment.getEmployee());
        for(Payment p: paymentsEmployee){
            if(payment.getPeriod().equals(p.getPeriod())){
                p.setSalary(payment.getSalary());
            }
        }
        StatusResponse newResponse = new StatusResponse();
        newResponse.setStatus("Added successfully!");
        return newResponse;
    }

    // Get a payment for a specific period and employee.
    @Override
    public EmpPaymentResponse getPaymentByPeriod(String period, User employee) {
        List<Payment> paymentsEmployee = this.paymentRepository.findAllByEmployee(employee.getEmail());
        Payment paymentEmployee = new Payment();

        try{
            Date periodDate = new SimpleDateFormat("MM-yyyy").parse(period);
            for(Payment p: paymentsEmployee){
                if(periodDate.compareTo(p.getPeriod())==0){
                    paymentEmployee.setId(p.getId());
                    paymentEmployee.setEmployee(p.getEmployee());
                    paymentEmployee.setPeriod(p.getPeriod());
                    paymentEmployee.setSalary(p.getSalary());
                }
            }
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong date format!");
        }


        if(paymentEmployee.getEmployee()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"period not found!");
        }
        DateFormat dateFormat = new SimpleDateFormat("MMMM-yyyy");
        String periodResponse = dateFormat.format( paymentEmployee.getPeriod());

        int dollars = paymentEmployee.getSalary().intValue()/100;
        int cents   = paymentEmployee.getSalary().intValue()%100;

        String salaryResponse = dollars+" dollar(s) "+cents+" cent(s)";


        return  new EmpPaymentResponse(employee.getName(), employee.getLastname(), periodResponse, salaryResponse);
    }

    // Get all payments for a specific employee.
    @Override
    public List<EmpPaymentResponse> getAllPayments(User employee) {
        List<Payment> paymentsEmployee = this.paymentRepository.findAllByEmployee(employee.getEmail());
        LinkedList<EmpPaymentResponse> payEmplResponse = new LinkedList<>();
        for(Payment p: paymentsEmployee){
            DateFormat dateFormat = new SimpleDateFormat("MMMM-yyyy");
            String periodResponse = dateFormat.format( p.getPeriod());

            int dollars = p.getSalary().intValue()/100;
            int cents   = p.getSalary().intValue()%100;
            String salaryResponse = dollars+" dollar(s) "+cents+" cent(s)";
            payEmplResponse.add(new EmpPaymentResponse(employee.getName(),employee.getLastname(),periodResponse,salaryResponse));
        }
        return payEmplResponse;
    }
}
