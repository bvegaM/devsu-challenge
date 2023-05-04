package com.devsu.challenge.dto.mapper;

import com.devsu.challenge.dto.movement.MovementDTO;
import com.devsu.challenge.model.Movement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @Mapping(source = "account.numberAccount", target = "numeroCuenta")
    @Mapping(source = "movementType", target = "tipoMovimiento")
    @Mapping(source = "value", target = "movimiento")
    @Mapping(source = "balanceInitial", target = "saldoInicial")
    @Mapping(source = "balanceAvailable", target = "saldoDisponible")
    @Mapping(source = "movementDate", target = "fechaMovimiento")
    @Mapping(source = "account.client.dni", target = "clienteCedula")
    @Mapping(source = "state", target = "estado")
    MovementDTO toMovementDto(Movement movement);
    List<MovementDTO> toMovementDtos(List<Movement> movements);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    Movement toMovement(MovementDTO movementDTO);
}
