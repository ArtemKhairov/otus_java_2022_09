package ru.otus.model;

import java.math.BigDecimal;

public class CartItem {
    private Product product;
    private int quantity;
    private BigDecimal price;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
