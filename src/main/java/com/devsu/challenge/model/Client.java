package com.devsu.challenge.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "T_CLIENTS")
public class Client extends Person{

    @Column(name = "CLI_PASSWORD", nullable = false)
    @NotNull(message = "La contrasenia es requerida")
    private String password;

    @Column(name = "CLI_STATE")
    private Boolean state;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Account> accounts;
}
