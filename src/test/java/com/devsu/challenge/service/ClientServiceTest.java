package com.devsu.challenge.service;

import com.devsu.challenge.dto.ClientDTO;
import com.devsu.challenge.dto.mapper.ClientMapper;
import com.devsu.challenge.model.Client;
import com.devsu.challenge.repository.ClientRepository;
import com.devsu.challenge.service.interfaces.ClientService;
import com.devsu.challenge.utils.enums.GenreEnum;
import com.devsu.challenge.utils.exceptions.DevsuException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Test
    @DisplayName("Test Save Client Succesfully")
    void testSaveClient() throws DevsuException{
        //@Given
        ClientDTO clientDTO = ClientDTO.builder().cedula("0150749059")
                .nombre("Bryam Vega")
                .estado(true)
                .clave("1234")
                .genero(GenreEnum.M)
                .direccion("Cuenca")
                .edad(24)
                .telefono("0985346351")
                .build();

        //@When
        when(this.clientRepository.save(any(Client.class))).thenReturn(clientMapper.toClient(clientDTO));
        ClientDTO clientSave = this.clientService.save(clientDTO);

        //@Then
        assertThat(clientSave.getCedula()).isSameAs(clientDTO.getCedula());
    }

    @Test
    @DisplayName("Test Save Client Exception ConstraintValidation")
    void testSaveClientConstraintValidationException() {
        //@Given
        ClientDTO clientDTO = ClientDTO.builder().cedula("0150749059")
                .nombre("Bryam Vega")
                .estado(true)
                .clave("1234")
                .genero(GenreEnum.M)
                .direccion("Cuenca")
                .edad(4)
                .telefono("0985346351")
                .build();

        //@When
        when(this.clientRepository.save(any(Client.class))).thenThrow(new ConstraintViolationException("", new HashSet<>()));

        //@Then
        Assertions.assertThrows(ConstraintViolationException.class, () -> clientService.save(clientDTO));
    }
}
