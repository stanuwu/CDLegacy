package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum Farming {
    MINING("Mining", ":pick:", Item.STONE, Item.DIAMOND),
    FORAGING("Foraging", ":axe:", Item.WOOD, Item.GOLDEN_LEAF),
    HUNTING("Hunting", ":bow_and_arrow:", Item.LEATHER, Item.WOLF_TOOTH),
    FISHING("Fishing", ":fishing_pole_and_fish:", Item.FISH_SCALE, Item.HYDRA_SCALE),
    ;
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
