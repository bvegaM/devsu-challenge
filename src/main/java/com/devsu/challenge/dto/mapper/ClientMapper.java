package com.devsu.challenge.dto.mapper;

import com.devsu.challenge.dto.ClientDTO;
import com.devsu.challenge.model.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "dni",target = "cedula")
    @Mapping(source = "name",target = "nombre")
    @Mapping(source = "genre",target = "genero")
    @Mapping(source = "age",target = "edad")
    @Mapping(source = "address",target = "direccion")
    @Mapping(source = "phoneNumber",target = "telefono")
    @Mapping(source = "password",target = "clave")
    @Mapping(source = "state",target = "estado")
    ClientDTO toClientDto(Client client);
    List<ClientDTO> toClientDtos(List<Client> clients);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Client toClient(ClientDTO clientDTO);
}
