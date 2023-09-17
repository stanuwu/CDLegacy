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

    public static Requirement level(int level) {
        return new Requirement(u -> u.getLevel() >= level, "Reach Level %d".formatted(level));
    }

    private final Function<DBUser, Boolean> function;
    private final String description;

    public Requirement(Function<DBUser, Boolean> function, String description) {
        this.function = function;
        this.description = description;
    }

    public String getDescription() {
        return this.description + " to unlock.";
    }

    public boolean test(DBUser user) {
        return function.apply(user);
    }
}
