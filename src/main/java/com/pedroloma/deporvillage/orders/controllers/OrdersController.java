package com.pedroloma.deporvillage.orders.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedroloma.deporvillage.orders.domain.Order;
import com.pedroloma.deporvillage.orders.domain.OrderNotFoundException;
import com.pedroloma.deporvillage.orders.repository.Service;

@RestController
public class OrdersController {

	@Autowired
	private Service service;

	/**
	 * Devuelve todas las órdenes.
	 * @return
	 */
	@GetMapping("/orders")
	public List<Order> listOfOrders() {
		return service.getAll();

	}
	
	/**
	 * Devuelve la orden del identificador.
	 * @param id
	 * @return
	 */
	@GetMapping("/orders/{id}")
	public Order getOrder(@PathVariable int id) {
		Order user = service.findOne(id);
		if (user == null)
			throw new OrderNotFoundException("Id - " + id);
			
		return user;
	}

	/**
	 * Inserta la orden
	 * @param order
	 * @return
	 */
	@PostMapping("/orders")
	public ResponseEntity<Object> newOrder(@RequestBody Order order) {
		Order savedOrder = service.save(order);
		URI uriLocation =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOrder.getId()).toUri();
		
		return ResponseEntity.created(uriLocation).build();
	}

}
