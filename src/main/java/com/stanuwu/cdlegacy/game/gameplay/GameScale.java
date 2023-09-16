package com.stanuwu.cdlegacy.game.gameplay;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GameScale {
    public int gearLevel(long exp) {
        return (int) Math.floor(Math.sqrt(exp / 5f));
    }

    public int playerLevel(long exp) {
        return (int) Math.floor(Math.sqrt(exp / 10f) / 2f);
    }

    public long guildLevel(long doors, long monsters, long bosses) {
        return (doors + 2 * monsters + 100 * bosses) / 1000;
    }

    public float guildBonus(long level) {
        return (float) (Math.sqrt(level / 5f) / 100f);
    }

    public int weaponDamage(int base, int level) {
        return base + (int) Math.floor(base * (Math.sqrt(level) / 10f));
    }

    public float armorBlock(int base, int level) {
        return base + (float) Math.floor(Math.sqrt(level) / 3f);
    }
}
