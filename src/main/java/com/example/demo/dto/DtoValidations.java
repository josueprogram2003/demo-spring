package com.example.demo.dto;

import java.math.BigDecimal;

final class DtoValidations {

    private DtoValidations() {
    }

    static void requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " is required");
        }
    }

    static void requireNotNull(Object value, String field) {
        if (value == null) {
            throw new IllegalArgumentException(field + " is required");
        }
    }

    static void validateBalance(BigDecimal balance) {
        requireNotNull(balance, "balance");
        if (balance.signum() < 0) {
            throw new IllegalArgumentException("balance must not be negative");
        }
    }
}
