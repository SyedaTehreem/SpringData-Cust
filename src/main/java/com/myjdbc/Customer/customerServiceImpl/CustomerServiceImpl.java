package com.myjdbc.Customer.customerServiceImpl;

import com.myjdbc.Customer.model.CustomerInfo;
import com.myjdbc.Customer.repository.CustomerRepository;
import com.myjdbc.Customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService {
@Autowired
    CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerInfo> getAllCustomers() {
        Iterable<CustomerInfo> customersIterable = customerRepository.findAll();
        List<CustomerInfo> customersList = StreamSupport.stream(customersIterable.spliterator(), false)
                .collect(Collectors.toList());
        return customersList;
    }


}
