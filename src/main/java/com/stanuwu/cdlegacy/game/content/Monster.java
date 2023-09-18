package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Monster {
    SLIME("Slime", "A mindless creature, formed through strange goo flowing out of the walls of certain caves. Comes in a variety of colours.", 0, 50, 0, 7),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final int minLevel;
    @Getter
    private final int health;
    @Getter
    private final int resistance;
    @Getter
    private final int damage;

    Monster(String name, String description, int minLevel, int health, int resistance, int damage) {
        this.name = name;
        this.description = description;
        this.minLevel = minLevel;
        this.health = health;
        this.resistance = resistance;
        this.damage = damage;
    }

    public static Monster randomForLevel(int level) {
        List<Monster> monsters = new ArrayList<>();
        for (Monster m : Monster.values()) {
            if (m.minLevel <= level) monsters.add(m);
        }
        return monsters.get(new Random().nextInt(0, monsters.size()));
    }
}
