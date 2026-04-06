package com.transaction.transaction.repo;

import com.transaction.transaction.QueryInterface.QueryDetailInterface;
import com.transaction.transaction.entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value="Select c.name as Name, c.email as Email, o.order_date as orderDate, o.total as total" +
            " from customers c, `orders` o where o.active_state=?1 And c.id = o.customer_id", nativeQuery = true)
    List<QueryDetailInterface> getAllOrderDetails(boolean status, Pageable pageable);

    @Query(value="Select count(*) from customers c, `orders` o where o.active_state=?1" +
            " And c.id = o.customer_id", nativeQuery = true)
    long countAllOrderDetails(boolean status);
}
