package com.immfly.payments.infrastructure.adapter.stock;

import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.model.StockService;
import org.springframework.stereotype.Service;

@Service
public class MockStockService implements StockService {

    @Override
    public boolean hasStock(Long productId) {
        // Mock implementation, I'm not going to implement a real stock service for this example
        return true;
    }
}
