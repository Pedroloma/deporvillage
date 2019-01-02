package com.pedroloma.ecommerce.orders.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedroloma.ecommerce.orders.domain.Order;
import com.pedroloma.ecommerce.orders.domain.OrderNotFoundException;
import com.pedroloma.ecommerce.orders.domain.Status;
import com.pedroloma.ecommerce.orders.repository.OrderRepository;

@RestController
public class OrdersController {

	// Conexión a la base de datos MongoDB.
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * Devuelve todos los pedidos.
	 * @return
	 */
	@GetMapping("/orders")
	public List<Order> listOfOrders() {
		return orderRepository.findAll();
	}
	
	/**
	 * Devuelve el pedido del identificador.
	 * @param id
	 * @return
	 */
	@GetMapping("/orders/{id}")
	public Order getOrder(@PathVariable Long id) {
		Optional<Order> user = orderRepository.findById(id);
		if (user == null)
			throw new OrderNotFoundException("Id - " + id);
			
		return user.get();
	}

	/**
	 * Inserta el pedido.
	 * @param order
	 * @return
	 */
	@PostMapping("/orders")
	public ResponseEntity<Object> newOrder(@RequestBody Order order) {
		long id = orderRepository.count();
		while (orderRepository.existsById(++id)) {}
			
		order.setId(id);
		Order savedOrder = orderRepository.save(order);
		URI uriLocation =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOrder.getId()).toUri();
		
		return ResponseEntity.created(uriLocation).build();
	}
	
	/**
	 * Actualiza el pedido al siguiente estado.
	 * @param id
	 * @return
	 */
	@PutMapping("/orders/{id}")
	public Order updateStatus(@PathVariable Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (!order.isPresent())
			throw new OrderNotFoundException("Id - " + id);

		if (order.get().getStatus().ordinal() == Status.DELIVERED.ordinal())
			throw new IllegalStateException("Order has already been delireved.");
		
		//Avanzamos el pedido al estado siguiente.
		order.get().setStatus(Status.values()[order.get().getStatus().ordinal() + 1]);
		orderRepository.save(order.get());
			
		return order.get();
		
	}
	
	/**
	 * Borra el pedido.
	 * @param id
	 */
	@DeleteMapping("/orders/{id}")
	public void deleteOrder(@PathVariable Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (!order.isPresent())
			throw new OrderNotFoundException("Id - " + id);
		
		// Sólo permitimos borrar si todavía no está confirmado. 
		//A partir de ese estado, no se puede borrar un pedido.
		if (order.get().getStatus().ordinal() > Status.PENDING_CONFIRMANTION.ordinal())
			throw new IllegalStateException("Only orders pending of confirmation can be removed.");
		
		orderRepository.deleteById(id);
	}

}
