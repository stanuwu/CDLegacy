package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import lombok.Getter;

public enum Armor implements IGear {
    RAGS("Rags", "Really dirty and torn apart.", Rarity.COMMON, 0),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Rarity rarity;
    @Getter
    private final int reduction;
    @Getter
    private final EventHook event;

    Armor(String name, String description, Rarity rarity, int reduction, EventHook event) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.reduction = reduction;
        this.event = event;
    }

    Armor(String name, String description, Rarity rarity, int reduction) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.reduction = reduction;
        this.event = EventHook.NONE;
    }
}
