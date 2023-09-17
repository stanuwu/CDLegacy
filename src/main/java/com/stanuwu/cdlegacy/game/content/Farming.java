package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum Farming {
    MINING("Mining", ":pick:", Item.STONE, Item.DIAMOND),
    FORAGING("Foraging", ":axe:", Item.WOOD, Item.GOLDEN_LEAF);
    @Getter
    private final String name;
    @Getter
    private final String emoji;
    @Getter
    private final Item common;
    @Getter
    private final Item rare;

    Farming(String name, String emoji, Item common, Item rare) {
        this.name = name;
        this.emoji = emoji;
        this.common = common;
        this.rare = rare;
    }
}
