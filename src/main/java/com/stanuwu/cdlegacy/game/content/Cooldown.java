package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum Cooldown {
    DOOR(15),
    TRAIN(5),
    FARM(30),
    VOTE(1440);
    @Getter
    private final int cd;

    Cooldown(int cd) {
        this.cd = cd;
    }
}