package com.example.demo.service.impl;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.AccountUpdateRequest;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository,
                              CustomerRepository customerRepository,
                              AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> findAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> findByCustomerId(UUID customerId) {
        return accountRepository.findByCustomer_Id(customerId).stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse findById(UUID id) {
        return accountMapper.toResponse(getEntity(id));
    }

    @Override
    public AccountResponse create(AccountRequest request) {
        if (accountRepository.existsByAccountNumber(request.accountNumber())) {
            throw new DuplicateResourceException("Account number already exists: " + request.accountNumber());
        }
        CustomerEntity customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + request.customerId()));

        AccountEntity account = accountMapper.toEntity(request);
        account.setCustomer(customer);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public AccountResponse update(UUID id, AccountUpdateRequest request) {
        AccountEntity account = getEntity(id);
        if (!account.getAccountNumber().equals(request.accountNumber())
                && accountRepository.existsByAccountNumber(request.accountNumber())) {
            throw new DuplicateResourceException("Account number already exists: " + request.accountNumber());
        }
        accountMapper.updateEntity(request, account);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public void delete(UUID id) {
        AccountEntity account = getEntity(id);
        account.setState(false);
        accountRepository.save(account);
    }

    private AccountEntity getEntity(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));
    }
}
