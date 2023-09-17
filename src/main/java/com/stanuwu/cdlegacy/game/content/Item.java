package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum Item {
    STONE("Stone"),
    DIAMOND("Diamond"),
    WOOD("Wood"),
    GOLDEN_LEAF("Golden Leaf"),
    ;
    @Getter
    private final String name;

    Item(String name) {
        this.name = name;
    }
}
