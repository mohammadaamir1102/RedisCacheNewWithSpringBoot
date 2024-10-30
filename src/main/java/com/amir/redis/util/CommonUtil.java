package com.amir.redis.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

    public static int calculateAge(String dob) {
        LocalDate birthDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            birthDate = LocalDate.parse(dob, formatter);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
