package com.pedroloma.deporvillage.orders.events;

import org.springframework.context.ApplicationEvent;

public class StatusEvent extends ApplicationEvent {

	private static final long serialVersionUID = -2185411862409781430L;

	public StatusEvent(Object source) {
		super(source);
	}

	@Override
	public String toString() {
		return "Status event ocurred.";
	}

	
}
