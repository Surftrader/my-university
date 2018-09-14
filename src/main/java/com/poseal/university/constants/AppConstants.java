package com.poseal.university.constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppConstants {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    public static final LocalDateTime TIME_MIN = LocalDateTime.of(1970, 1, 1, 0, 0);
    public static final LocalDateTime TIME_MAX = LocalDateTime.of(9999, 1, 1, 0, 0);

}
