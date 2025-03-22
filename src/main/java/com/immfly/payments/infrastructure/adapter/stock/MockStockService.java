package com.immfly.payments.infrastructure.adapter.stock;

import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.model.StockService;
import org.springframework.stereotype.Service;

@Service
public class MockStockService implements StockService {
    @Override
    public boolean isProductInStock(Product product) {
        // Replace with real stock logic or repository call
        return true;
    }
}
