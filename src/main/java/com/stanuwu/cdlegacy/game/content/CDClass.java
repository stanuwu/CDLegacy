package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Requirement;
import lombok.Getter;

public enum CDClass {
    ADVENTURER("Adventurer", "Starter class, receives a 5% exp bonus.", new EventHook()),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final EventHook action;
    @Getter
    private final Requirement requirement;

    CDClass(String name, String description, EventHook action, Requirement requirement) {
        this.name = name;
        this.description = description;
        this.requirement = requirement;
        this.action = action;
    }

    CDClass(String name, String description, EventHook action) {
        this.name = name;
        this.description = description;
        this.action = action;
        this.requirement = Requirement.TRUE;
    }
}
