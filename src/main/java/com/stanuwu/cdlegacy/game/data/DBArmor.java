package com.stanuwu.cdlegacy.game.data;

import com.stanuwu.cdlegacy.game.content.Armor;
import com.stanuwu.cdlegacy.game.gameplay.GameScale;
import lombok.Getter;

import java.util.Locale;

public class DBArmor {
    @Getter
    private volatile Armor type;
    @Getter
    private volatile long exp;

    DBArmor(Armor type, long exp) {
        this.type = type;
        this.exp = exp;
    }

    public synchronized void set(Armor armor) {
        this.type = armor;
        this.exp = 0;
    }

    public synchronized void addExp(int exp) {
        this.exp += exp;
    }


    public int getLevel() {
        return GameScale.gearLevel(this.getExp());
    }

    public float getBlock() {
        int base = this.getType().getReduction();
        if (base == 0) return 0;
        return Math.max(GameScale.armorBlock(this.getType().getReduction(), this.getLevel()), 50) / 100;
    }

    public String formatName() {
        return "%s [Lvl. %d]".formatted(this.getType().getName(), this.getLevel());
    }

    public String formatLevel() {
        return String.format(Locale.US, "Lvl. %d [Exp. %,d]", this.getLevel(), this.exp);
    }

    public String formatBlock() {
        return String.format(Locale.US, "%.2f", this.getBlock() * 100);
    }
}
