package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(UUID id, String accountNumber, BigDecimal balance) {
}
