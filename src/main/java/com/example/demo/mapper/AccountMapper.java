package com.example.demo.mapper;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.AccountUpdateRequest;
import com.example.demo.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponse toResponse(AccountEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "customer", ignore = true)
    AccountEntity toEntity(AccountRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "customer", ignore = true)
    void updateEntity(AccountUpdateRequest request, @MappingTarget AccountEntity entity);
}
