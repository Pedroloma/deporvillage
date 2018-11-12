package com.pedroloma.deporvillage.orders.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pedroloma.deporvillage.orders.domain.Address;
import com.pedroloma.deporvillage.orders.domain.Item;
import com.pedroloma.deporvillage.orders.domain.Order;

@Component
public class Service {
	private static List<Order> orders = new ArrayList<>();
	
	private static Long orderCounter = Long.valueOf(3);
	
	static {
		orders.add(new Order(Long.valueOf(1000), new BigDecimal(100), new Item(1,new BigDecimal(10),1), new Address(), new Address()));
		orders.add(new Order(Long.valueOf(1001), new BigDecimal(200), new Item(2,new BigDecimal(11),2), new Address(), new Address()));
		orders.add(new Order(Long.valueOf(1002), new BigDecimal(300), new Item(3,new BigDecimal(12),3), new Address(), new Address()));		
	}
	
	public List<Order> getAll() {
		return orders;
	}
	
	public Order save(Order order) {
		if (order.getId() == null)
			order.setId(++orderCounter);
		
		orders.add(order);
		
		return order;
		
	}
	
	public Order findOne(int i) {
		for (Order order : orders) {
			if (order.getId() == i) {
				return order;
			}
		}
		return null;
	}
	
}
