package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

public enum CDClass {
    ADVENTURER("Adventurer", "Receive a 5% EXP bonus.", new EventHook(
            Events.OBTAIN_EXP.make(e -> e.exp.op(ex -> (int) (ex * 1.05)))
    )),
    SAGE("Sage", "Receive a 7% EXP bonus.", new EventHook(
            Events.OBTAIN_EXP.make(e -> e.exp.op(ex -> (int) (ex * 1.07)))
    ), Requirement.LEGACY),
    THIEF("Thief", "Receive a 5% coin bonus.", new EventHook(
            Events.OBTAIN_COINS.make(e -> e.coins.op(c -> (int) (c * 1.05)))
    )),
    BRAWLER("Brawler", "Receive a 5% blunt weapon damage bonus.", new EventHook(

    ), Requirement.level(5)),
    FARMER("Farmer", "Get an additional item while farming.", new EventHook(
            Events.OBTAIN_ITEM.make(e -> e.count.op(c -> ++c))
    ), Requirement.level(10)),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final EventHook event;
    @Getter
    private final Requirement requirement;

    CDClass(String name, String description, EventHook event, Requirement requirement) {
        this.name = name;
        this.description = description;
        this.requirement = requirement;
        this.event = event;
    }

    CDClass(String name, String description, EventHook event) {
        this.name = name;
        this.description = description;
        this.event = event;
        this.requirement = Requirement.TRUE;
    }
}
