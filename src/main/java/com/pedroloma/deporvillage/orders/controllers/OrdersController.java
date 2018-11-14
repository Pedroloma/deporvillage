package com.pedroloma.deporvillage.orders.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedroloma.deporvillage.orders.domain.Order;
import com.pedroloma.deporvillage.orders.domain.OrderNotFoundException;
import com.pedroloma.deporvillage.orders.domain.Status;
import com.pedroloma.deporvillage.orders.repository.OrderRepository;
import com.pedroloma.deporvillage.orders.repository.Service;

@RestController
public class OrdersController {

	@Autowired
	private Service service;
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * Devuelve todas las órdenes.
	 * @return
	 */
	@GetMapping("/orders")
	public List<Order> listOfOrders() {
//		return service.getAll();
//		orderRepository.save(new Order(Long.valueOf(1), new BigDecimal(100), new Item(1,new BigDecimal(10),1), new Address(), new Address()));
//		orderRepository.save(new Order(Long.valueOf(2), new BigDecimal(200), new Item(2,new BigDecimal(11),2), new Address(), new Address()));
//		orderRepository.save(new Order(Long.valueOf(3), new BigDecimal(300), new Item(3,new BigDecimal(12),3), new Address(), new Address()));
//		orderRepository.save(new Order(Long.valueOf(4), new BigDecimal(300), new Item(3,new BigDecimal(12),3), new Address(), new Address()));

		return orderRepository.findAll();

	}
	
	/**
	 * Devuelve la orden del identificador.
	 * @param id
	 * @return
	 */
	@GetMapping("/orders/{id}")
	public Order getOrder(@PathVariable int id) {
//		Order user = service.findOne(id);
		Optional<Order> user = orderRepository.findById(Long.valueOf(id));
		if (user == null)
			throw new OrderNotFoundException("Id - " + id);
			
		return user.get();
	}

	/**
	 * Inserta la orden
	 * @param order
	 * @return
	 */
	@PostMapping("/orders")
	public ResponseEntity<Object> newOrder(@RequestBody Order order) {
//		Order savedOrder = service.save(order);
		order.setId(orderRepository.count()+1);
		Order savedOrder = orderRepository.save(order);
		URI uriLocation =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOrder.getId()).toUri();
		
		return ResponseEntity.created(uriLocation).build();
	}
	
	@PutMapping("/orders/{id}")
	public Order updateStatus(@PathVariable int id) {
//		Order order = service.updateStatus(id);
		Optional<Order> order = orderRepository.findById(Long.valueOf(id));
		if (!order.isPresent())
			throw new OrderNotFoundException("Id - " + id);

		order.get().setStatus(Status.values()[order.get().getStatus().ordinal() + 1]);
		orderRepository.save(order.get());
			
		return order.get();
		
	}

}
