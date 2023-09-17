package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum Title {
    PLAYER("Player"),
    BEGINNER("Beginner"),
    NOVICE("Novice", Requirement.level(5)),
    INTERMEDIATE("Intermediate", Requirement.level(10)),
    LEGACY("Timeworn", Requirement.LEGACY),
    N("<3", Requirement.N),
    DEVELOPER("Dev", Requirement.OWNER),
    ;
    @Getter
    private final String title;
    @Getter
    private final Requirement requirement;

    Title(String title) {
        this.title = title;
        this.requirement = Requirement.TRUE;
    }

    Title(String title, Requirement requirement) {
        this.title = title;
        this.requirement = requirement;
    }
}
