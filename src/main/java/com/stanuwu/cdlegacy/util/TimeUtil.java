package com.stanuwu.cdlegacy.util;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDateTime;

@UtilityClass
public class TimeUtil {
    public final LocalDateTime MIN = LocalDateTime.of(2000, 1, 1, 0, 0, 0);

    public long minuteDifference(LocalDateTime time, LocalDateTime time2) {
        return Duration.between(time, time2).toMinutes();
    }
}
