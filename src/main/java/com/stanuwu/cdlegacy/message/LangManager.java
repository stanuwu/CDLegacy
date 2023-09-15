package com.stanuwu.cdlegacy.message;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class LangManager {
    private final Map<String, String> strings = new HashMap<>();
    private final Logger logger = new SimpleLoggerFactory().getLogger("Lang");

    public String getRaw(String key) {
        return strings.computeIfAbsent(key, f -> {
            try (InputStream stream = LangManager.class.getResourceAsStream("/lang/" + key)) {
                if (stream == null) {
                    logger.warn("File missing: " + key);
                    return key;
                }
                return new String(stream.readAllBytes());
            } catch (IOException e) {
                logger.warn("File missing: " + key);
                return key;
            }
        });
    }

    public String get(String key, Placeholder... placeholders) {
        String string = getRaw(key);
        for (Placeholder p : placeholders) {
            string = string.replaceAll("%" + p.key() + "%", p.value());
        }
        return string;
    }
}
