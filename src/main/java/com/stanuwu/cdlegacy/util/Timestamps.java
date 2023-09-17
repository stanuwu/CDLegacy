package com.stanuwu.cdlegacy.util;

import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.utils.TimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class Timestamps {
    public String toTimestamp(TimeFormat format, LocalDateTime time) {
        return format.atTimestamp(time.toInstant(ZoneOffset.UTC).toEpochMilli()).toString();
    }
}
