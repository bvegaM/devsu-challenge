package com.devsu.challenge.utils;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class Constants {


    public static final HttpStatus STATUS_OK = HttpStatus.OK;
    public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    public static final HttpStatus CREATED_SUCCESS = HttpStatus.CREATED;
    public static final BigDecimal DEBIT_LIMIT = BigDecimal.valueOf(1000.00);
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String CLIENT_NOT_FOUND_MESSAGE = "Cliente no encontrado";
    public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Cuenta no encontrada";


    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
