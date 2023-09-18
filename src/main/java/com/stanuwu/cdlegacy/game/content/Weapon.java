package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import lombok.Getter;

public enum Weapon {
    STICK("Wooden Stick", "Could be used to hit things.", Rarity.COMMON, 3, DamageType.BLUNT),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Rarity rarity;
    @Getter
    private final int damage;
    @Getter
    private final DamageType damageType;
    @Getter
    private final EventHook event;

    Weapon(String name, String description, Rarity rarity, int damage, DamageType damageType, EventHook event) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.damage = damage;
        this.damageType = damageType;
        this.event = event;
    }

    Weapon(String name, String description, Rarity rarity, int damage, DamageType damageType) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.damage = damage;
        this.damageType = damageType;
        this.event = EventHook.NONE;
    }

    public String formatType() {
        return this.getDamageType().getName() + " (" + this.getDamageType().getArchtype().getName() + ")";
    }
}
