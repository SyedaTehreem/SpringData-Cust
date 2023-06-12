package com.myjdbc.Customer.controller;

import com.myjdbc.Customer.model.CustomerInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.myjdbc.Customer.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
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
/////for inserting record
    @PostMapping("/add")
    public ResponseEntity<CustomerInfo> addCustomer(@RequestBody @Valid CustomerInfo customerInfo) {
        CustomerInfo createdCustomer = customerService.addCustomer(customerInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerInfo> updateCustomer(@PathVariable int customerId, @RequestBody CustomerInfo customerInfo) {

        CustomerInfo updatedCustomer = customerService.updateCustomer(customerId, customerInfo);
        return ResponseEntity.ok(updatedCustomer);
    }

/////Delete
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        boolean deleted = customerService.deleteCustomer(customerId);

        if (deleted) {
            return ResponseEntity.ok("Customer deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /////Get A Recrod
    @GetMapping("/edit/{id}")
    public ResponseEntity<CustomerInfo> getCustomerById(@PathVariable Integer id) {
        CustomerInfo customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //// Form submission end point:
    @PostMapping("/submitForm")
    public ResponseEntity<CustomerInfo> submitForm(@RequestBody @Valid CustomerInfo customerInfo) {
        CustomerInfo createdCustomer = customerService.addCustomer(customerInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }


}
