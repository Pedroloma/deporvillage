package com.pedroloma.deporvillage.orders.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;

import com.pedroloma.deporvillage.orders.events.StatusEvent;

//@Entity
@Controller
public class Order implements ApplicationEventPublisherAware{

	@javax.persistence.Id
	private Long Id;
	private BigDecimal totalAmount;
	private Set<Item> items = new HashSet<Item>();
	private Address addressShipping;
	private Address addressBilling;
	private Status status;
	
	private ApplicationEventPublisher publisher;

	public Order() {
	}

	public Order(Long Id, BigDecimal totalAmount, Item item, Address addressShipping, Address addressBilling) {
		super();
		this.Id = Id;
		this.totalAmount = totalAmount;
		this.items.add(item);
		this.addressShipping = addressShipping;
		this.addressBilling = addressBilling;
		this.status = Status.PENDING_CONFIRMANTION;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Address getAddressShipping() {
		return addressShipping;
	}

	public void setAddressShipping(Address addressShipping) {
		this.addressShipping = addressShipping;
	}

	public Address getAddressBilling() {
		return addressBilling;
	}

	public void setAddressBilling(Address addressBilling) {
		this.addressBilling = addressBilling;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
//		StatusEvent event = new StatusEvent(this);
//		publisher.publishEvent(event);
	}

	@Override
	public String toString() {
		return "Order [Id=" + Id + ", totalAmount=" + totalAmount + ", items=" + items + ", addressShipping="
				+ addressShipping + ", addressBilling=" + addressBilling + ", status=" + status + "]";
	}

	public void markConfirmed() {
		this.status = Status.CONFIRMED;
	}

	public void markSentToWarehouse() {
		this.status = Status.SENT_TO_WAREHOUSE;
	}

	public void markShipped() {
		this.status = Status.SHIPPED;
	}

	public void markInTransit() {
		this.status = Status.IN_TRANSIT;
	}

	public void markDelivered() {
		this.status = Status.DELIVERED;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

}
