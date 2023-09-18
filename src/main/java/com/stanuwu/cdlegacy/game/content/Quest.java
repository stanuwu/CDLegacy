package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

import java.util.Random;

public enum Quest {
    NONE("No Quest Active", "You don't have an active quest.", 1, 1, 0, 0, EventHook.NONE),
    SLAY("Slay Monsters", "Slay %diff% more monsters.", 5, 5, 100, 50, new EventHook(
            Events.SLAY_MONSTER.make(e -> e.user.getQuest().progress())
    )),
    EXPLORE("Explore", "Gain %diff% more exp.", 100, 5, 50, 25, new EventHook(
            Events.OBTAIN_EXP.make(e -> e.user.getQuest().progress(e.exp.get()))
    )),
    TRAIN("Train", "Train %diff% more times.", 2, 3, 50, 25, new EventHook(
            Events.TRAIN.make(e -> e.user.getQuest().progress())
    )),
    FARM("Farm", "Farm %diff% more times.", 1, 3, 100, 50, new EventHook(
            Events.FARM.make(e -> e.user.getQuest().progress())
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

    public static Quest random() {
        int quest = new Random().nextInt(1, Quest.values().length);
        return Quest.values()[quest];
    }

    public boolean isNone() {
        return this == Quest.NONE;
    }
}
