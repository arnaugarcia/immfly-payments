package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.exception.DomainException;
import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.model.StockService;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.domain.repository.ProductRepository;
import com.immfly.payments.infrastructure.dto.OrderDTO;
import com.immfly.payments.infrastructure.dto.OrderRequestDTO;

public class UpdateOrderUseCase {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StockService stockService;

    public UpdateOrderUseCase(OrderRepository orderRepository, ProductRepository productRepository, StockService stockService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.stockService = stockService;
    }

    public OrderDTO addProductToOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        if (!stockService.hasStock(product.id())) {
            throw new DomainException("Product " + product.name() + " is out of stock.");
        }
        order.addProduct(product);
        Order result = orderRepository.save(order);
        return OrderDTO.fromDomain(result);
    }

    public OrderDTO updateOrder(Long orderId, OrderRequestDTO orderDto) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.update(orderDto.buyerEmail());
        Order result = orderRepository.save(order);
        return OrderDTO.fromDomain(result);
    }
}
