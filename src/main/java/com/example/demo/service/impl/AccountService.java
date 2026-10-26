package com.example.demo.service.impl;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.AccountUpdateRequest;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    List<AccountResponse> findAll();

    List<AccountResponse> findByCustomerId(UUID customerId);

    AccountResponse findById(UUID id);

    AccountResponse create(AccountRequest request);

    AccountResponse update(UUID id, AccountUpdateRequest request);

    void delete(UUID id);
}
