package com.stanuwu.cdlegacy.game.gameplay;

import lombok.Getter;

public class Simulation {
    private final GameEntity attacker;
    private final GameEntity defender;
    @Getter
    private int attackerHealth;
    @Getter
    private int defenderHealth;

    public Simulation(GameEntity attacker, GameEntity defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.attackerHealth = attacker.getHealth();
        this.defenderHealth = defender.getHealth();
    }

    public boolean fight() {
        while (true) {
            defenderHealth -= attacker.getDamage() * (1 - defender.getBlock());
            if (defenderHealth <= 0) {
                defenderHealth = 0;
                return true;
            }
            attackerHealth -= defender.getDamage() * (1 - attacker.getBlock());
            if (attackerHealth <= 0) {
                attackerHealth = 0;
                return false;
            }
        }
    }
}
