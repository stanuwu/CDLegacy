package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.Requirement;
import lombok.Getter;

public enum Title {
    PLAYER("Player"),
    GAMER("Gamer", Requirement.level(10)),
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
