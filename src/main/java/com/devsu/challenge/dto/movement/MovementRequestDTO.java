package com.devsu.challenge.dto.movement;

import com.devsu.challenge.utils.enums.MovementTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MovementRequestDTO {

    @NotNull(message = "El numero de cuenta es requerido")
    private String numeroCuenta;
    @Min(value = 1,message = "El saldo inicial debe ser mayor o igual que 1")
    @NotNull(message = "El valor del movimiento es requerido")
    private BigDecimal valor;
    @NotNull(message = "El tipo de movimiento es requerido")
    private MovementTypeEnum tipoMovimiento;
}
