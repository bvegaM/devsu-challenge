package com.devsu.challenge.dto.mapper;

import com.devsu.challenge.dto.AccountDTO;
import com.devsu.challenge.model.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "numberAccount", target = "numeroCuenta")
    @Mapping(source = "typeAccount", target = "tipoCuenta")
    @Mapping(source = "initialBalance", target = "saldoInicial")
    @Mapping(source = "state", target = "estado")
    @Mapping(source = "client.dni", target = "clienteCedula")
    AccountDTO toAccountDto(Account account);
    List<AccountDTO> toAccountDtos(List<Account> accounts);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "movements", ignore = true)
    Account toAccount(AccountDTO accountDTO);
}
