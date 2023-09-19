package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.Config;
import com.stanuwu.cdlegacy.game.data.DBUser;

import java.util.function.Function;

public class Requirement {
    public static final Requirement TRUE = new Requirement(u -> true, "No Requirements");
    public static final Requirement FALSE = new Requirement(u -> false, "Unable");
    public static final Requirement LEGACY = new Requirement(u -> Config.isLegacy(u.getUserId()), "Be a legacy user");
    public static final Requirement N = new Requirement(u -> u.getUserId() == Config.N, "Be special");
    public static final Requirement OWNER = new Requirement(u -> Config.isStaff(u.getUserId()), "Own CustomDungeons");
    public static final Requirement FIRE_ORB = new Requirement(u -> u.getExtra().getType() == Extra.FIRE_ORB, "Own the Fire Orb");
    public static final Requirement UNIQUE = new Requirement(u -> u.getWeapon().getType().getRarity() == Rarity.UNIQUE || u.getArmor().getType().getRarity() == Rarity.UNIQUE || u.getExtra().getType().getRarity() == Rarity.UNIQUE, "Own any unique gear");
    public static final Requirement PROUD = new Requirement(u -> u.getWeapon().getType().getRarity() == Rarity.CORRUPTED && u.getArmor().getType().getRarity() == Rarity.CORRUPTED && u.getExtra().getType().getRarity() == Rarity.CORRUPTED, "Own all corrupted gear");

    public static Requirement level(int level) {
        return new Requirement(u -> u.getLevel() >= level, "Reach Level %d".formatted(level));
    }

    public static Requirement slain(int monsters) {
        return new Requirement(u -> u.getMonstersSlain() >= monsters, "Slay %d Monsters".formatted(monsters));
    }

    public static Requirement opened(int doors) {
        return new Requirement(u -> u.getDoorsOpened() >= doors, "Open %d Doors".formatted(doors));
    }

    public static Requirement found(int gear) {
        return new Requirement(u -> u.getItemsFound() >= gear, "Find %d Gear Pieces".formatted(gear));
    }

    private final Function<DBUser, Boolean> function;
    private final String description;

    public Requirement(Function<DBUser, Boolean> function, String description) {
        this.function = function;
        this.description = description;
    }

    public String getDescription() {
        return this.description + " to unlock." ;
    }

    public boolean test(DBUser user) {
        return function.apply(user);
    }
}
