package com.stanuwu.cdlegacy.game.data;

import lombok.experimental.UtilityClass;
import org.apache.commons.text.similarity.LevenshteinDistance;

@UtilityClass
public class DBEnum {
    public <T extends Enum<T>> T fromKey(String key, Class<T> clazz) {
        return T.valueOf(clazz, key);
    }

    public String toKey(Enum<?> value) {
        return value.name();
    }

    public <T extends Enum<T>> T closestMatch(String query, T def) {
        int dist = Integer.MAX_VALUE;
        T res = def;
        for (T value : def.getDeclaringClass().getEnumConstants()) {
            int newDist = LevenshteinDistance.getDefaultInstance().apply(query.toLowerCase(), value.name().toLowerCase());
            if (newDist < dist) {
                dist = newDist;
                res = value;
            }
        }
        return res;
    }
}
