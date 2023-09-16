package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum DamageArchtype {
    PHYSICAL_CLOSE("Close"),
    PHYSICAL_FAR("Ranged"),
    MAGIC("Magic"),
    ;
    @Getter
    private final String name;

    DamageArchtype(String name) {
        this.name = name;
    }
}
