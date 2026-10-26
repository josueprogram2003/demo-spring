package com.example.demo.service.impl;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerResponse> findAll();

    CustomerResponse findById(UUID id);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(UUID id, CustomerRequest request);

    void delete(UUID id);
}
