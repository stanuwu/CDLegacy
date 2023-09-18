package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import lombok.Getter;

public enum Extra implements IGear {
    PENDANT("Pendant", "A small, ochre amulet.", Rarity.COMMON),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Rarity rarity;
    @Getter
    private final EventHook event;

    Extra(String name, String description, Rarity rarity, EventHook event) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.event = event;
    }

    Extra(String name, String description, Rarity rarity) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.event = EventHook.NONE;
    }
}
