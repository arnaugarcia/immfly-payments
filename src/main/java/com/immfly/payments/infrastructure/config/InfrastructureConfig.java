package com.immfly.payments.infrastructure.config;

import com.immfly.payments.application.usecase.CancelOrderUseCase;
import com.immfly.payments.application.usecase.CreateOrderUseCase;
import com.immfly.payments.application.usecase.FinishOrderUseCase;
import com.immfly.payments.application.usecase.GetAllCategoriesUseCase;
import com.immfly.payments.application.usecase.GetAllProductsUseCase;
import com.immfly.payments.application.usecase.UpdateOrderUseCase;
import com.immfly.payments.domain.model.StockService;
import com.immfly.payments.domain.repository.CategoryRepository;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.domain.repository.ProductRepository;
import com.immfly.payments.infrastructure.adapter.payment.PaymentGatewayFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfig {

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderRepository orderRepository) {
        return new CreateOrderUseCase(orderRepository);
    }

    @Bean
    public UpdateOrderUseCase updateOrderUseCase(OrderRepository orderRepository,
                                                 ProductRepository productRepository,
                                                 StockService stockService) {
        return new UpdateOrderUseCase(orderRepository, productRepository, stockService);
    }

    @Bean
    public FinishOrderUseCase finishOrderUseCase(OrderRepository orderRepository, PaymentGatewayFactory paymentGatewayFactory) {
        return new FinishOrderUseCase(orderRepository, paymentGatewayFactory);
    }

    @Bean
    public GetAllCategoriesUseCase getAllCategoriesUseCase(CategoryRepository categoryRepository) {
        return new GetAllCategoriesUseCase(categoryRepository);
    }

    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductRepository productRepository) {
        return new GetAllProductsUseCase(productRepository);
    }

    @Bean
    public CancelOrderUseCase cancelOrderUseCase(OrderRepository orderRepository) {
        return new CancelOrderUseCase(orderRepository);
    }
}

