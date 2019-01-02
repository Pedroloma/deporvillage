package com.pedroloma.ecommerce.orders.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error personalizado para las peticiones que no encuentran el pedido solicitado.
 * @author pedro
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message) {
		super(message);
	}
	
}
