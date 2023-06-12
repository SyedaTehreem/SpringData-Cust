package com.myjdbc.Customer.service;
import com.myjdbc.Customer.model.CustomerInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
    List<CustomerInfo> getAllCustomers();
    CustomerInfo addCustomer(CustomerInfo customerInfo);

    CustomerInfo updateCustomer(int customerId, CustomerInfo customerInfo);

    boolean deleteCustomer(int customerId);

    CustomerInfo getCustomerById(Integer id);
}
