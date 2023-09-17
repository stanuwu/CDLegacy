package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

public enum Quest {
    NONE("No Quest Active", "You dont have an active quest.", 1, 1, 0, 0, EventHook.NONE),
    SLAY("Slay Monsters", "Slay %diff% some monsters.", 5, 5, 500, 250, new EventHook(
            Events.SLAY_MONSTER.make(e -> e.user.getQuest().progress())
    )),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final int requirement;
    @Getter
    private final int maxLvl;
    @Getter
    private final int money;
    @Getter
    private final int exp;
    @Getter
    private final EventHook event;

    Quest(String name, String description, int requirement, int maxLvl, int money, int exp, EventHook event) {
        this.name = name;
        this.description = description;
        this.requirement = requirement;
        this.maxLvl = maxLvl;
        this.money = money;
        this.exp = exp;
        this.event = event;
    }
}
