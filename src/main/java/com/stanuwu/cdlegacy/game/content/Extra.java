package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import lombok.Getter;

public enum Extra {
    PENDANT("Pendant", "A small, ochre amulet.", Rarity.COMMON, DamageType.BLUNT),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Rarity rarity;
    @Getter
    private final DamageType damageType;
    @Getter
    private final EventHook event;

    Extra(String name, String description, Rarity rarity, DamageType damageType, EventHook event) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.damageType = damageType;
        this.event = event;
    }

    Extra(String name, String description, Rarity rarity, DamageType damageType) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.damageType = damageType;
        this.event = EventHook.NONE;
    }
}
