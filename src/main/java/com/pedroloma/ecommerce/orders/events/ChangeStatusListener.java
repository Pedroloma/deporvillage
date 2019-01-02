package com.pedroloma.ecommerce.orders.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusListener implements ApplicationListener<ChangeStatusEvent> {

	/**
	 * Se recibe el evento de cambio de status.
	 * En función de cada cambio, teniendo en cuenta que se suponen secuenciales,
	 * se realizan las acciones necesarias, en cada caso.
	 */
	@Override
	public void onApplicationEvent(ChangeStatusEvent event) {
		System.out.println("**************************************************************");
		System.out.println(event.toString());
		System.out.println("**************************************************************");
	}

}
