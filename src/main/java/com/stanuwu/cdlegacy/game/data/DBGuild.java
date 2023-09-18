package com.stanuwu.cdlegacy.game.data;

import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.gameplay.GameScale;
import lombok.Getter;

import java.util.Locale;

public class DBGuild {
    private final EventHook event = new EventHook();
    @Getter
    private final long guildId;
    @Getter
    private volatile long doorsOpened;
    @Getter
    private volatile long monstersSlain;
    @Getter
    private volatile long bossesSlain;

    public DBGuild(long guildId, long doorsOpened, long monstersSlain, long bossesSlain) {
        this.guildId = guildId;
        this.doorsOpened = doorsOpened;
        this.monstersSlain = monstersSlain;
        this.bossesSlain = bossesSlain;
    }

    public long getLevel() {
        return GameScale.guildLevel(this.getDoorsOpened(), this.getMonstersSlain(), this.getBossesSlain());
    }

    public float xpBonus() {
        return GameScale.guildBonus(this.getLevel());
    }

    public synchronized void openDoor() {
        this.doorsOpened++;
    }

    public synchronized void slayMonster() {
        this.monstersSlain++;
    }

    public synchronized void slayBoss() {
        this.bossesSlain++;
    }

    public String formatName(String name) {
        return "[Lvl. %d] %s".formatted(this.getLevel(), name);
    }

    public String formatBonus() {
        return String.format(Locale.US, "%.2f", this.xpBonus() * 100);
    }

    public <T extends Event> void invokeEvent(Events.EventCaller<T> caller, T event) {
        this.event.accept(caller, event);
    }
}
