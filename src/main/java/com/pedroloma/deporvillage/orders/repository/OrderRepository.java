package com.pedroloma.deporvillage.orders.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pedroloma.deporvillage.orders.domain.Order;
import com.pedroloma.deporvillage.orders.domain.Status;

public interface OrderRepository extends MongoRepository<Order, Long> {
	
	public List<Order> findByStatus(Status status);

}
