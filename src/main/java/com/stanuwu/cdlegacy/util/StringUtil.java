package com.stanuwu.cdlegacy.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {
    public String clean(String string) {
        return string.replaceAll("[^[A-Za-z0-9\\,\\.\\-\\_\\:\\'\\ \\(\\)\\\"\\!]]", "");
    }

    public String truncate(String string, int length) {
        if (string.length() < length) return string;
        return string.substring(0, length);
    }
}
