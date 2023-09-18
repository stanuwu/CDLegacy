package com.stanuwu.cdlegacy.game.gameplay;

import lombok.Getter;

import java.util.Locale;

public abstract class GameEntity {
    @Getter
    protected int health;
    @Getter
    protected int damage;
    @Getter
    protected float block;

    public String formatBlock() {
        return String.format(Locale.US, "%.2f", this.getBlock() * 100);
    }
}
