package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;
import static com.example.demo.dto.DtoValidations.requireNotNull;
import static com.example.demo.dto.DtoValidations.requireText;
import static com.example.demo.dto.DtoValidations.validateBalance;

public record AccountRequest(String accountNumber, BigDecimal balance, UUID customerId) {

    public AccountRequest {
        requireText(accountNumber, "accountNumber");
        validateBalance(balance);
        requireNotNull(customerId, "customerId");
    }
}
