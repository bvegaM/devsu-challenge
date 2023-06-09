package com.devsu.challenge.model;

import com.devsu.challenge.utils.enums.AccountTypeEnum;
import javax.persistence.*;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "T_ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACT_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ACT_NUMBER", unique = true, nullable = false)
    private String numberAccount;

    @Column(name = "ACT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum typeAccount;

    @Column(name = "ACT_BALANCE", nullable = false)
    @Min(value = 1,message = "El saldo inicial debe ser mayor o igual 1")
    private BigDecimal initialBalance;

    @Column(name = "ACT_STATE")
    private Boolean state;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
    @JoinColumn(name = "ACT_CLI_ID", referencedColumnName = "PER_ID")
    private Client client;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Movement> movements;

}
