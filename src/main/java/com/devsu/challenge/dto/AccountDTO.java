package com.devsu.challenge.dto;

import com.devsu.challenge.utils.enums.AccountTypeEnum;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AccountDTO {

    @NotNull(message = "El numero de cuenta es requerido")
    private String numeroCuenta;
    @NotNull(message = "El tipo de cuenta es requerido")
    private AccountTypeEnum tipoCuenta;
    @Min(value = 1,message = "El saldo inicial debe ser mayor o igual que 1")
    @NotNull(message = "El saldo inicial es requerido")
    private BigDecimal saldoInicial;
    @NotNull(message = "La identificacion del client es requerida")
    private String clienteCedula;
    @NotNull(message = "El estado es requerido")
    private Boolean estado;
}
