package com.pedroloma.deporvillage;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pedroloma.deporvillage.orders.domain.Address;
import com.pedroloma.deporvillage.orders.domain.Item;
import com.pedroloma.deporvillage.orders.domain.Order;
import com.pedroloma.deporvillage.orders.domain.Status;
import com.pedroloma.deporvillage.orders.repository.OrderRepository;

@SpringBootApplication
public class DeporvillageApplication implements CommandLineRunner{

	@Autowired
	private OrderRepository orderRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DeporvillageApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		orderRepository.deleteAll();
		
		orderRepository.save(new Order(Long.valueOf(1), new BigDecimal(100), new Item(1,new BigDecimal(10),1), new Address(), new Address()));
		orderRepository.save(new Order(Long.valueOf(2), new BigDecimal(200), new Item(2,new BigDecimal(11),2), new Address(), new Address()));
		orderRepository.save(new Order(Long.valueOf(3), new BigDecimal(300), new Item(3,new BigDecimal(12),3), new Address(), new Address()));
		
		for (Order order : orderRepository.findAll()) {
			System.out.println(order.toString());
		}
		
		for (Order order : orderRepository.findByStatus(Status.CONFIRMED)) {
			System.out.println(order);
		} 
	}
}
