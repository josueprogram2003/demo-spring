package com.example.demo.repository;

import com.example.demo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    List<AccountEntity> findByCustomer_Id(UUID customerId);

    boolean existsByAccountNumber(String accountNumber);
}
