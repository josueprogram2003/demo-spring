package com.example.demo.dto;

import java.util.List;
import java.util.UUID;

public record CustomerResponse(UUID id, String name, String lastName, String email, List<AccountResponse> accounts) {
}
