package com.myjdbc.Customer.repository;

import com.myjdbc.Customer.model.CustomerInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerInfo, Integer> {
    List<CustomerInfo> findAll();



}
