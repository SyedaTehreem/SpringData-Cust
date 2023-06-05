package com.myjdbc.Customer.service;
import com.myjdbc.Customer.model.CustomerInfo;
import java.util.List;

public interface CustomerService {
    List<CustomerInfo> getAllCustomers();
}
