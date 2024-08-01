package com.lg.electronic_store.utils.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(FORMAT);
    }
}