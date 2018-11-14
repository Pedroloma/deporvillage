package com.pedroloma.deporvillage.orders.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pedroloma.deporvillage.orders.domain.Address;
import com.pedroloma.deporvillage.orders.domain.Item;
import com.pedroloma.deporvillage.orders.domain.Order;
import com.pedroloma.deporvillage.orders.domain.Status;

@Component
public class Service {
	private static List<Order> orders = new ArrayList<>();

	private static Long orderCounter = Long.valueOf(3);

	static {
		orders.add(new Order(Long.valueOf(1), new BigDecimal(100), new Item(1,new BigDecimal(10),1), new Address(), new Address()));
		orders.add(new Order(Long.valueOf(2), new BigDecimal(200), new Item(2,new BigDecimal(11),2), new Address(), new Address()));
		orders.add(new Order(Long.valueOf(3), new BigDecimal(300), new Item(3,new BigDecimal(12),3), new Address(), new Address()));		
	}

	/**
	 * Devuelve la lista de órdenes.
	 * @return
	 */
	public List<Order> getAll() {
		return orders;
	}

	/**
	 * Guarda la orden en la lista de órdenes.
	 * @param order
	 * @return la orden
	 */
	public Order save(Order order) {
		if (order.getId() == null)
			order.setId(++orderCounter);

		orders.add(order);

		return order;

	}

	/**
	 * Busca la orden por el identificador.
	 * @param i
	 * @return la orden
	 */
	public Order findOne(int i) {
		for (Order order : orders) {
			if (order.getId() == i) {
				return order;
			}
		}
		return null;
	}

	/**
	 * Actualizamos el status de la orden. Presuponemos que es secuencial y que tiene que pasar por todos los estados. 
	 * @param id
	 * @return
	 */
	public Order updateStatus(int id) {
		for (Order order : orders) {
			if (order.getId() == id) {
				if (Status.values().length > order.getStatus().ordinal() + 1) {
					order.setStatus(Status.values()[order.getStatus().ordinal() + 1]);
					return order;
				}
			}
		}
		return null;
	}

}
