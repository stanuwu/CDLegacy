package com.stanuwu.cdlegacy.game.event;

import com.stanuwu.cdlegacy.game.data.DBUser;
import lombok.Getter;

import java.util.function.Function;

public class Requirement {
    public static final Requirement TRUE = new Requirement(u -> true, "No Requirements");
    public static final Requirement FALSE = new Requirement(u -> false, "Unobtainable");

    public static Requirement level(int level) {
        return new Requirement(u -> u.getLevel() >= level, "Reach Level %d".formatted(level));
    }

    private final Function<DBUser, Boolean> function;
    @Getter
    private final String description;

    public Requirement(Function<DBUser, Boolean> function, String description) {
        this.function = function;
        this.description = description;
    }

    public boolean test(DBUser user) {
        return function.apply(user);
    }
}
