package com.devsu.challenge.model;

import com.devsu.challenge.utils.enums.GenreEnum;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "T_PERSONS")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "PER_DNI", unique = true, nullable = false)
    @Size(min = 10, max = 10, message = "La cedula debe tener 10 digitos")
    @NotNull(message = "La cedula es requerida")
    private String dni;

    @Column(name = "PER_NAME", nullable = false)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre solo debe contener letras")
    @NotNull(message = "El nombre es requerido")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "PER_GENRE", nullable = false)
    @NotNull(message = "El genero es requerido")
    private GenreEnum genre;

    @Column(name = "PER_AGE", nullable = false)
    @Min(value = 18,message = "La edad debe ser minima de 18")
    @Max(value = 70, message = "La edad debe ser maxima de 70")
    @NotNull(message = "La edad es requerida")
    private Integer age;

    @Column(name = "PER_ADDRESS", nullable = false)
    @NotNull(message = "La direccion es requerida")
    private String address;

    @Column(name = "PER_PHONE", nullable = false)
    @Pattern(regexp = "[0-9]+", message = "El telefono solo debe contener numeros")
    @NotNull(message = "El telefono es requerido")
    private String phoneNumber;

}
