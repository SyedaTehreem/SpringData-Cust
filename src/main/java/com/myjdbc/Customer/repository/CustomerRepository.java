package com.myjdbc.Customer.repository;

import com.myjdbc.Customer.model.CustomerInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerInfo, Integer> {
    List<CustomerInfo> findAll();
    Optional<CustomerInfo> findById(Integer id);

}
