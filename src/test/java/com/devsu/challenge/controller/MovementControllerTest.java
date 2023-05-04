package com.devsu.challenge.controller;


import com.devsu.challenge.dto.AccountDTO;
import com.devsu.challenge.dto.ClientDTO;
import com.devsu.challenge.dto.movement.MovementDTO;
import com.devsu.challenge.dto.movement.MovementRequestDTO;
import com.devsu.challenge.service.interfaces.AccountService;
import com.devsu.challenge.service.interfaces.ClientService;
import com.devsu.challenge.service.interfaces.MovementService;
import com.devsu.challenge.utils.ResponseHandler;
import com.devsu.challenge.utils.enums.AccountTypeEnum;
import com.devsu.challenge.utils.enums.GenreEnum;
import com.devsu.challenge.utils.enums.MovementTypeEnum;
import com.devsu.challenge.utils.exceptions.DevsuException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovementControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MovementService movementService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Test
    void testSaveMovement() throws DevsuException {
        // Given
        ClientDTO clientDTO = ClientDTO.builder().cedula("0150749059")
                .nombre("Bryam Vega")
                .estado(true)
                .clave("1234")
                .genero(GenreEnum.M)
                .direccion("Cuenca")
                .edad(24)
                .telefono("0985346351")
                .build();
        AccountDTO accountDTO = AccountDTO.builder().clienteCedula("0150749059")
                .numeroCuenta("1")
                .saldoInicial(BigDecimal.valueOf(1000))
                .tipoCuenta(AccountTypeEnum.AHORRO)
                .estado(true).build();

        MovementRequestDTO movementRequestDTO = MovementRequestDTO.builder()
                .numeroCuenta("1")
                .valor(BigDecimal.valueOf(1000))
                .tipoMovimiento(MovementTypeEnum.DEPOSITO)
                .build();



        //@When
        clientService.save(clientDTO);
        accountService.save(accountDTO);
        ResponseEntity<ResponseHandler> responseEntity =
                restTemplate.postForEntity("/movimientos", movementRequestDTO,ResponseHandler.class);

        //@Then
        System.out.println(responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        List<MovementDTO> movements = movementService.findMovementsByAccountNumberAccount("1");
        assertThat(movements).hasSize(1);
    }
}
