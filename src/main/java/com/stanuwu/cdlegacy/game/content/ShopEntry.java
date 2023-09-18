package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

import java.util.function.Supplier;

public enum ShopEntry {
    COMMON_CHEST(Rarity.COMMON),
    UNCOMMON_CHEST(Rarity.UNCOMMON),
    RARE_CHEST(Rarity.RARE),
    EPIC_CHEST(Rarity.EPIC),
    LEGENDARY_CHEST(Rarity.LEGENDARY),
    UNSTABLE_CHEST(Rarity.UNSTABLE),
    CORRUPTED_CHEST(Rarity.CORRUPTED),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Rarity rarity;
    @Getter
    private final int price;
    private final Supplier<IGear> procureItem;


    ShopEntry(String name, String description, Rarity rarity, int price, Supplier<IGear> procureItem) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.price = price;
        this.procureItem = procureItem;
    }

    ShopEntry(Rarity rarity) {
        this.name = rarity.getName() + " Chest";
        this.description = "Whatever could be inside?";
        this.rarity = rarity;
        this.price = rarity.getBuyPrice();
        this.procureItem = rarity::getRandomItemFor;
    }

    ShopEntry(IGear gear) {
        this.name = gear.getName();
        this.description = gear.getDescription();
        this.rarity = gear.getRarity();
        this.price = gear.getRarity().getBuyPrice();
        this.procureItem = () -> gear;
    }

    ShopEntry(IGear gear, int price) {
        this.name = gear.getName();
        this.description = gear.getDescription();
        this.rarity = gear.getRarity();
        this.price = price;
        this.procureItem = () -> gear;
    }

    public IGear procureItem() {
        return this.procureItem.get();
    }
}
