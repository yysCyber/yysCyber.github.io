package edu.khai.tp.uav.calculator.util;

import java.util.UUID;

public final class StringUtils {

    public static String generateUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}