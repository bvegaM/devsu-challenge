package com.devsu.challenge.utils.enums;

public enum MovementTypeEnum {

    DEPOSITO("credito"),
    DEBITO("debito");

    private String typeMovement;

    MovementTypeEnum(String typeMovement) {
        this.typeMovement = typeMovement;
    }

}
