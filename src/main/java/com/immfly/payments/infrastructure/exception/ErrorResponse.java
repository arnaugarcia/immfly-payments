package com.immfly.payments.infrastructure.exception;


import java.time.LocalDateTime;

record ErrorResponse(String message, LocalDateTime timestamp) { }
