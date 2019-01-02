package com.pedroloma.ecommerce.orders.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pedroloma.ecommerce.orders.domain.Order;
import com.pedroloma.ecommerce.orders.domain.Status;

/**
 * Interface para la conexión con la base de datos MongoDB.
 * @author pedro
 *
 */
public interface OrderRepository extends MongoRepository<Order, Long> {
	
	//Query para buscar pedidos por estado.
	public List<Order> findByStatus(Status status);

}
