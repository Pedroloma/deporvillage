package com.pedroloma.deporvillage.orders.domain;

import java.math.BigDecimal;

public class Item {
	private Integer SKU;
	private BigDecimal price;
	private int quantity;

	protected Item() {
		super();
	}

	public Item(Integer SKU, BigDecimal price, int quantity) {
		super();
		this.SKU = SKU;
		this.price = price;
		this.quantity = quantity;
	}

	public Integer getSKU() {
		return SKU;
	}

	public void setSKU(Integer sKU) {
		SKU = sKU;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [SKU=" + SKU + ", price=" + price + ", quantity=" + quantity + "]";
	}

	
}
