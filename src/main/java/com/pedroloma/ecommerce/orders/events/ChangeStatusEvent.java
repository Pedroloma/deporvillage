package com.pedroloma.ecommerce.orders.events;

import org.springframework.context.ApplicationEvent;

import com.pedroloma.ecommerce.orders.domain.Order;

/**
 * Evento que se lanza siempre que se cambia el status de una orden.
 *
 */
public class ChangeStatusEvent extends ApplicationEvent {

	private static final long serialVersionUID = -2185411862409781430L;
	private final Long orderId;
	
	public Long getOrderId() {
		return orderId;
	}

	public ChangeStatusEvent(Order source) {
		super(source);
		
		this.orderId = source.getId();
	}

	@Override
	public String toString() {
		return "Status event ocurred. Id: " + orderId;
	}

	
}
