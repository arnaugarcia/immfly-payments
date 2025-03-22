package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.exception.DomainException;
import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.model.StockService;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.domain.repository.ProductRepository;

public class UpdateOrderUseCase {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StockService stockService;

    public UpdateOrderUseCase(OrderRepository orderRepository, ProductRepository productRepository, StockService stockService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.stockService = stockService;
    }

    public Order addProductToOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        if (!stockService.isProductInStock(product)) {
            throw new DomainException("Product " + product.name() + " is out of stock.");
        }
        order.addProduct(product);
        return orderRepository.save(order);
    }
}
