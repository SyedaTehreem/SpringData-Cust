package com.myjdbc.Customer.controller;

import com.myjdbc.Customer.model.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myjdbc.Customer.service.CustomerService;

import java.util.List;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")

    public ResponseEntity<List<CustomerInfo>> getAllCustomers() {
        List<CustomerInfo> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
