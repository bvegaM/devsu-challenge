package com.devsu.challenge.service;

import com.devsu.challenge.dto.AccountDTO;
import com.devsu.challenge.dto.mapper.AccountMapper;
import com.devsu.challenge.repository.AccountRepository;
import com.devsu.challenge.service.interfaces.AccountService;
import com.devsu.challenge.utils.enums.AccountTypeEnum;
import com.devsu.challenge.utils.exceptions.DevsuException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @MockBean
    private AccountRepository accountRepository;


    @Test
    @DisplayName("Test Find Account By Id Successfully")
    void testFindById() throws DevsuException {
        //@Given
        Integer accountId = 1;
        AccountDTO accountDTO = AccountDTO.builder().clienteCedula("0150749059")
                .numeroCuenta("1")
                .saldoInicial(BigDecimal.valueOf(1000))
                        .tipoCuenta(AccountTypeEnum.AHORRO)
                                .estado(true).build();

        //@When
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(accountMapper.toAccount(accountDTO)));


        //@Then
        AccountDTO accountResponse = accountService.findById(accountId);
        assertThat(accountResponse.getNumeroCuenta()).isSameAs(accountDTO.getNumeroCuenta());
    }

    @Test
    @DisplayName("Test Find Account By Id Exception")
    void testFindByIdException(){
        //@Given
        Integer accountId = 1;

        //@When
        when(accountRepository.findById(accountId)).thenThrow(new RuntimeException());

        //@Then
        Assertions.assertThrows(DevsuException.class, () -> accountService.findById(accountId));
    }
}
