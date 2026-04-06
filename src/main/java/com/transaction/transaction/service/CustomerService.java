package com.transaction.transaction.service;

import com.transaction.transaction.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    void deleteCustomer(Long id);
}
