package com.devsu.challenge.dto.mapper;

import com.devsu.challenge.dto.movement.ReportDTO;
import com.devsu.challenge.model.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {


    @Mapping(source = "movementDate", target = "fecha")
    @Mapping(source = "account.client.name", target = "cliente")
    @Mapping(source = "account.numberAccount", target = "numeroCuenta")
    @Mapping(source = "account.typeAccount", target = "tipo")
    @Mapping(source = "balanceInitial", target = "saldoInicial")
    @Mapping(source = "state", target = "estado")
    @Mapping(source = "value", target = "movimiento")
    @Mapping(source = "balanceAvailable", target = "saldoDisponible")
    ReportDTO toReportDto(Movement movement);
    List<ReportDTO> toReportDtos(List<Movement> movements);
}
