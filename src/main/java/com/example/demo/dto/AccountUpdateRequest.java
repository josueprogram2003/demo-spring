package com.example.demo.dto;

import java.math.BigDecimal;
import static com.example.demo.dto.DtoValidations.requireText;
import static com.example.demo.dto.DtoValidations.validateBalance;

public record AccountUpdateRequest(String accountNumber, BigDecimal balance) {

    public AccountUpdateRequest {
        requireText(accountNumber, "accountNumber");
        validateBalance(balance);
    }
}
