package com.transaction.transaction.repo;

import com.transaction.transaction.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can add custom queries later if needed
}
