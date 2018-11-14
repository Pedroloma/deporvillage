package com.pedroloma.deporvillage.orders.domain;

/**
 * Posibles estados de una pedido.
 * @author pedro
 *
 */
public enum Status {
	PENDING_CONFIRMANTION,
	CONFIRMED,
	SENT_TO_WAREHOUSE,
	SHIPPED,
	IN_TRANSIT,
	DELIVERED
}
