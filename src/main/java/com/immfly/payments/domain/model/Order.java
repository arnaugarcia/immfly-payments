package com.immfly.payments.domain.model;

import com.immfly.payments.domain.exception.DomainException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {
    private final Long id;
    private final List<Product> products = new ArrayList<>();
    private BigDecimal totalPrice;
    private final String seatLetter;
    private final Integer seatNumber;
    private String buyerEmail;
    private Payment payment;
    private OrderStatus status = OrderStatus.OPEN;

    public Order(Long id, String seatLetter, Integer seatNumber) {
        if (seatLetter == null || seatLetter.isBlank()) {
            throw new DomainException("Seat letter is required");
        }
        if (seatNumber == null || seatNumber <= 0) {
            throw new DomainException("Seat number must be positive");
        }
        this.id = id;
        this.seatLetter = seatLetter;
        this.seatNumber = seatNumber;
        this.totalPrice = BigDecimal.ZERO;
    }

    public static Order create(String seatLetter, Integer seatNumber) {
        return new Order(null, seatLetter, seatNumber);
    }

    public void addProduct(Product product) {
        products.add(product);
        updateTotal();
    }

    public void update(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    private void updateTotal() {
        totalPrice = products.stream()
            .map(Product::price)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void cancel() {
        status = OrderStatus.CANCELED;
    }

    public void finishOrder(Payment payment) {
        this.payment = payment;
        status = OrderStatus.FINISHED;
    }

    public void dropped() {
        status = OrderStatus.DROPPED;
    }

    public Long id() { return id; }
    public List<Product> products() { return products; }
    public BigDecimal totalPrice() { return totalPrice; }
    public String seatLetter() { return seatLetter; }
    public Integer seatNumber() { return seatNumber; }
    public String buyerEmail() { return buyerEmail; }
    public Payment payment() { return payment; }
    public OrderStatus status() { return status; }
}
