package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum Item {
    STONE("Stone"),
    DIAMOND("Diamond"),
    WOOD("Wood"),
    GOLDEN_LEAF("Golden Leaf"),
    LEATHER("Leather"),
    WOLF_TOOTH("Wolf Tooth"),
    FISH_SCALE("Fish Scale"),
    HYDRA_SCALE("Hydra Scale"),
    ASHES("Ashes"),
    ;
    @Getter
    private final String name;

    Item(String name) {
        this.name = name;
    }
}
