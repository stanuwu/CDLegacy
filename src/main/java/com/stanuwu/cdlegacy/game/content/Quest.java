package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

import java.util.Random;

public enum Quest {
    NONE("No Quest Active", "You don't have an active quest.", 1, 1, 0, 0, EventHook.NONE),
    SLAY("Monster Slayer", "Slay %diff% more monster(s).", 3, 5, 100, 50, new EventHook(
            Events.SLAY_MONSTER.make(e -> e.user.getQuest().progress())
    )),
    EXPLORE("Explorer", "Gain %diff% more EXP.", 100, 5, 50, 25, new EventHook(
            Events.OBTAIN_EXP.make(e -> e.user.getQuest().progress(e.exp.get()))
    )),
    BUSINESSMAN("Businessman", "Gain %diff% more coins.", 200, 5, 50, 25, new EventHook(
            Events.OBTAIN_COINS.make(e -> e.user.getQuest().progress(e.coins.get()))
    )),
    TRAVELLER("Traveller", "Open %diff% more doors.", 2, 3, 75, 35, new EventHook(
            Events.OPEN_DOOR.make(e -> e.user.getQuest().progress())
    )),
    TRAIN("Trainer", "Train %diff% more time(s).", 4, 3, 50, 25, new EventHook(
            Events.TRAIN.make(e -> e.user.getQuest().progress())
    )),
    FARM("Farmer", "Farm %diff% more time(s).", 1, 3, 100, 50, new EventHook(
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
