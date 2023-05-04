package com.devsu.challenge.dto;

import com.devsu.challenge.utils.enums.GenreEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClientDTO {

    @Size(min = 10, max = 10, message = "La cedula debe tener 10 digitos")
    private String cedula;
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre solo debe contener letras")
    private String nombre;
    private GenreEnum genero;
    @Min(value = 18,message = "La edad debe ser minima de 18")
    @Max(value = 70, message = "La edad debe ser maxima de 70")
    private Integer edad;
    private String direccion;
    @Pattern(regexp = "[0-9]+", message = "El telefono solo debe contener numeros")
    private String telefono;
    private String clave;
    private Boolean estado;
}
