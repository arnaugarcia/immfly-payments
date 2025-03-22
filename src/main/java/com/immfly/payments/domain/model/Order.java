package com.immfly.payments.domain.model;

import com.immfly.payments.domain.exception.DomainException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void addProduct(Product product) {
        products.add(product);
        updateTotal();
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

    public Long id() { return id; }
    public List<Product> products() { return products; }
    public BigDecimal totalPrice() { return totalPrice; }
    public String seatLetter() { return seatLetter; }
    public Integer seatNumber() { return seatNumber; }
    public String buyerEmail() { return buyerEmail; }
    public Payment payment() { return payment; }
    public OrderStatus status() { return status; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
            Objects.equals(products, order.products) &&
            Objects.equals(totalPrice, order.totalPrice) &&
            Objects.equals(seatLetter, order.seatLetter) &&
            Objects.equals(seatNumber, order.seatNumber) &&
            Objects.equals(buyerEmail, order.buyerEmail) &&
            Objects.equals(payment, order.payment) &&
            status == order.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(products);
        result = 31 * result + Objects.hashCode(totalPrice);
        result = 31 * result + Objects.hashCode(seatLetter);
        result = 31 * result + Objects.hashCode(seatNumber);
        result = 31 * result + Objects.hashCode(buyerEmail);
        result = 31 * result + Objects.hashCode(payment);
        result = 31 * result + Objects.hashCode(status);
        return result;
    }
}
