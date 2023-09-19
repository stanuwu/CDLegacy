package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum Title {
    PLAYER("Player"),
    BEGINNER("Beginner"),
    NOVICE("Novice", Requirement.level(5)),
    INTERMEDIATE("Intermediate", Requirement.level(10)),
    EXPERIENCED("Experienced", Requirement.level(15)),
    MASTER("Master", Requirement.level(30)),
    OBSESSED("Obsessed", Requirement.level(50)),
    FIGHTER("Fighter", Requirement.slain(10)),
    SLAYER("Slayer", Requirement.slain(100)),
    BLOODTHIRSTY("Bloodthirsty", Requirement.slain(1000)),
    PACIFIST("Pacifist", Requirement.slain(10000)),
    SCAVENGER("Scavenger", Requirement.opened(10)),
    DUNGEONEER("Dungeoneer", Requirement.opened(100)),
    CRYPT_BOSS("Crypt Boss", Requirement.opened(1000)),
    LOST("Lost", Requirement.opened(10000)),
    LOOTER("Looter", Requirement.found(1)),
    SHINY("Shiny", Requirement.found(10)),
    GREEDY("Greedy", Requirement.found(100)),
    RICH("Rich", Requirement.found(1000)),
    PROUD("Proud", Requirement.PROUD),
    UWU("uwu", Requirement.UNIQUE),
    LEGACY("Timeworn", Requirement.LEGACY),
    N("<3", Requirement.N),
    DEVELOPER("Dev", Requirement.OWNER),
    TRUE_GOD("True God", Requirement.FIRE_ORB),
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
