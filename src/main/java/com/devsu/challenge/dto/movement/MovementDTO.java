package com.devsu.challenge.dto.movement;

import com.devsu.challenge.utils.enums.MovementTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MovementDTO {

    private String numeroCuenta;
    private MovementTypeEnum tipoMovimiento;
    private BigDecimal saldoInicial;
    private BigDecimal saldoDisponible;
    private BigDecimal movimiento;
    @JsonFormat(pattern="yyy-MM-dd hh:mm:ss")
    private LocalDateTime fechaMovimiento;
    private String clienteCedula;
    private Boolean estado;
}
