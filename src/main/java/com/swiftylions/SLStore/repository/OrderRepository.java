package com.swiftylions.SLStore.repository;

import com.swiftylions.SLStore.entity.Customer;
import com.swiftylions.SLStore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // fetch orders for a customer, sorted by creation date in descending order.
    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);

    List<Order> findByOrderStatus(String orderStatus);

    //JPQL Query
    @Query("SELECT o FROM Order o WHERE o.customer=:customer ORDER BY o.createdAt DESC")
    List<Order> findOrdersByCustomer(@Param("customer") Customer customer);

    @Query("SELECT o FROM Order o WHERE o.orderStatus=?1")
    List<Order> findOrderByStatus(String orderStatus);

    //Native Query
    @Query(value = "SELECT * FROM orders o WHERE o.customer_id=:customerId ORDER BY o.created_at DESC"
    , nativeQuery = true)
    List<Order> findOrdersByCustomerWithNativeQuery(@Param("customerId") Long customerId);

    @Query(value = "SELECT * FROM orders o WHERE o.order_status=?1"
            ,nativeQuery = true)
    List<Order> findOrderByStatusWithNativeQuery(String orderStatus);

    //update status
    @Modifying //to tell jpa this is modifying data query not a read data query
    @Transactional //to roll back transaction in case of errors in middle of running query
    @Query("UPDATE Order o SET o.orderStatus=:orderStatus,o.updatedAt=CURRENT_TIMESTAMP,o.updatedBy=:updatedBy WHERE o.orderId=:orderId")
    void updateOrderStatus(@Param("orderId") Long orderId,@Param("orderStatus") String orderStatus,@Param("updatedBy") String updatedBy);
    //int = returns number of records effected by updating operation
    //void = if you don't want to accept any data
  }