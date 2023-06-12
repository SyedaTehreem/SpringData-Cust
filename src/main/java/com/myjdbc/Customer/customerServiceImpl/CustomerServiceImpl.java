package com.myjdbc.Customer.customerServiceImpl;

import com.myjdbc.Customer.model.CustomerInfo;
import com.myjdbc.Customer.repository.CustomerRepository;
import com.myjdbc.Customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
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

    // FOR Getting a single record from customer table
    @Override
    public CustomerInfo getCustomerById(Integer id) {
        Optional<CustomerInfo> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    ////Saving info
    @Override
    public CustomerInfo addCustomer(CustomerInfo customerInfo) {
        // Implement the logic to add a new customer
        // Save the customerInfo object to the database
        CustomerInfo createdCustomer = customerRepository.save(customerInfo);
        return createdCustomer;
    }

    /////Update the record
    @Override

    public CustomerInfo updateCustomer(int customerId, CustomerInfo updatedCustomerInfo) {
        Optional<CustomerInfo> oldCustomer = customerRepository.findById(customerId);
        if (oldCustomer.isPresent()) {
            CustomerInfo existingCustomer = oldCustomer.get();
            existingCustomer.setId(updatedCustomerInfo.getId());
            existingCustomer.setName(updatedCustomerInfo.getName());
            existingCustomer.setLast_name(updatedCustomerInfo.getLast_name());
            existingCustomer.setAddress(updatedCustomerInfo.getAddress());
            existingCustomer.setCity(updatedCustomerInfo.getCity());
            existingCustomer.setCountry(updatedCustomerInfo.getCountry());
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }



    //////Delete implementation
    @Override
    public boolean deleteCustomer(int customerId) {
        // Implement the logic to delete the customer
        // Return true if the deletion is successful, false otherwise
        try {
            customerRepository.deleteById(customerId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
