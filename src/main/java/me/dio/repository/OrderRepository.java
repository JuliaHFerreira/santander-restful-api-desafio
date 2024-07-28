package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.model.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long>{

	boolean existsByOrderNumber(String orderNumber);

}
