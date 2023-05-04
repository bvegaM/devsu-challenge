package com.devsu.challenge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static Date getCurrentDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        return formatter.parse(new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date()));
    }

    public static Date convertStringToDate(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        return formatter.parse(date);
    }

    private Util() {
        throw new IllegalStateException("Utility class");
    }
}
