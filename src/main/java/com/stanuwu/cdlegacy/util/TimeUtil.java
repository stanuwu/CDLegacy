package com.stanuwu.cdlegacy.util;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDateTime;

@UtilityClass
public class TimeUtil {
    public long minuteDifference(LocalDateTime time, LocalDateTime time2) {
        return Duration.between(time, time2).toMinutes();
    }
}
