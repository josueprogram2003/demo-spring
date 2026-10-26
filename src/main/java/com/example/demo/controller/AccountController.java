package com.example.demo.controller;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.AccountUpdateRequest;
import com.example.demo.service.impl.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Cuentas", description = "Alta, consulta, actualización y baja lógica de cuentas")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountResponse> findAll(@RequestParam(value = "customerId", required = false) UUID customerId) {
        return customerId == null
                ? accountService.findAll()
                : accountService.findByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable("id") UUID id) {
        return accountService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@RequestBody AccountRequest request) {
        return accountService.create(request);
    }

    @PutMapping("/{id}")
    public AccountResponse update(@PathVariable("id") UUID id, @RequestBody AccountUpdateRequest request) {
        return accountService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        accountService.delete(id);
    }
}
