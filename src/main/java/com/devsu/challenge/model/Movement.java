package com.devsu.challenge.model;

import com.devsu.challenge.utils.enums.MovementTypeEnum;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "T_MOVEMENTS")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOV_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "MOV_DATE", nullable = false)
    private Date movementDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "MOV_TYPE", nullable = false)
    private MovementTypeEnum movementType;

    @Column(name = "MOV_VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "MOV_BALANCE_IN", nullable = false)
    private BigDecimal balanceInitial;

    @Column(name = "MOV_BALANCE_AV", nullable = false)
    private BigDecimal balanceAvailable;

    @Column(name = "MOV_STATE", nullable = false)
    private Boolean state;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "MOV_ACT_ID", referencedColumnName = "ACT_ID")
    private Account account;

}
