package com.immfly.payments.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    private Long productId;

    private Integer quantity;
}
