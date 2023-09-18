package com.stanuwu.cdlegacy.game.data;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DBEnum {
    public <T extends Enum<T>> T fromKey(String key, Class<T> clazz) {
        return T.valueOf(clazz, key);
    }

    public String toKey(Enum<?> value) {
        return value.name();
    }
}
