package com.stanuwu.cdlegacy.game.data;

import com.stanuwu.cdlegacy.game.content.Extra;
import com.stanuwu.cdlegacy.game.gameplay.GameScale;
import lombok.Getter;

import java.util.Locale;

public class DBExtra {
    @Getter
    private volatile Extra type;
    @Getter
    private volatile long exp;

    DBExtra(Extra type, long exp) {
        this.type = type;
        this.exp = exp;
    }

    public synchronized void set(Extra extra) {
        this.type = extra;
        this.exp = 0;
    }

    public int getLevel() {
        return GameScale.gearLevel(this.getExp());
    }

    public String formatName() {
        return "%s [Lvl. %d]".formatted(this.getType().getName(), this.getLevel());
    }

    public String formatLevel() {
        return String.format(Locale.US, "Lvl. %d [Exp. %,d]", this.getLevel(), this.exp);
    }
}
