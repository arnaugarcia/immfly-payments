package com.immfly.payments.infrastructure.dto;

public record OrderRequestDTO(String buyerEmail, String cardToken, String gateway) {

}
