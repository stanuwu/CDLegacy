package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import lombok.Getter;

public enum CDClass {
    ADVENTURER("Adventurer", "Receives a 5% exp bonus.", new EventHook()),
    SAGE("Sage", "Receives a 7% exp bonus.", new EventHook(), Requirement.LEGACY),
    THIEF("Thief", "Receives a 5% coin bonus.", new EventHook()),
    BRAWLER("Brawler", "Receives a 5% blunt weapon damage bonus.", new EventHook(), Requirement.level(5)),
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
