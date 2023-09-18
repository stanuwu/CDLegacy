package com.stanuwu.cdlegacy.game.data;

import com.stanuwu.cdlegacy.game.content.Weapon;
import com.stanuwu.cdlegacy.game.gameplay.GameScale;
import lombok.Getter;

import java.util.Locale;

public class DBWeapon {
    @Getter
    private volatile Weapon type;
    @Getter
    private volatile long exp;

    DBWeapon(Weapon type, long exp) {
        this.type = type;
        this.exp = exp;
    }

    public synchronized void set(Weapon weapon) {
        this.type = weapon;
        this.exp = 0;
    }

    public synchronized void addExp(int exp) {
        this.exp += exp;
    }

    public int getLevel() {
        return GameScale.gearLevel(this.getExp());
    }

    public int getDamage() {
        return GameScale.weaponDamage(this.getType().getDamage(), this.getLevel());
    }

    public String formatName() {
        return "%s [Lvl. %d]".formatted(this.getType().getName(), this.getLevel());
    }

    public String formatLevel() {
        return String.format(Locale.US, "Lvl. %d [Exp. %,d]", this.getLevel(), this.exp);
    }
}
