package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

import java.util.Random;

public enum Rarity {
    COMMON("Common", true, 100, 50, 15, 10000),
    UNCOMMON("Uncommon", true, 500, 100, 25, 3500),
    RARE("Rare", true, 5000, 1000, 250, 1500),
    EPIC("Epic", true, 10000, 2500, 650, 500),
    LEGENDARY("Legendary", true, 30000, 10000, 2500, 150),
    UNSTABLE("Unstable", true, 50000, 25000, 6500, 20),
    CORRUPTED("Corrupted", true, 100000, 50000, 15000, 2),
    UNIQUE("Unique", false, Integer.MAX_VALUE, 100000, 25000, -1),
    CRAFTED("Crafted", false, Integer.MAX_VALUE, 0, 0, -1),
    ;
    @Getter
    private final String name;
    @Getter
    private final boolean canBuy;
    @Getter
    private final int buyPrice;
    @Getter
    private final int sellPrice;
    @Getter
    private final int infuseValue;
    @Getter
    private final int dropChance10k;

    Rarity(String name, boolean canBuy, int buyPrice, int sellPrice, int infuseValue, int dropChance10k) {
        this.name = name;
        this.canBuy = canBuy;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.infuseValue = infuseValue;
        this.dropChance10k = dropChance10k;
    }

    public Rarity getRandomRarity() {
        int rng = new Random().nextInt(0, 10000);
        Rarity current = COMMON;
        int min = 10000;
        for (Rarity r : Rarity.values()) {
            if (rng < r.dropChance10k && r.dropChance10k < min) {
                min = r.dropChance10k;
                current = r;
            }
        }
        return current;
    }
}
