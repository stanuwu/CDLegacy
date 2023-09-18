package com.stanuwu.cdlegacy.game.data;

import com.stanuwu.cdlegacy.game.content.Quest;
import lombok.Getter;

import java.util.Random;

public class DBQuest {
    @Getter
    private volatile Quest type;
    @Getter
    private volatile int level;
    @Getter
    private volatile int progress;

    DBQuest(Quest type, int level, int progress) {
        this.type = type;
        this.level = level;
        this.progress = progress;
    }

    public int req() {
        return this.getType().getRequirement() * this.getLevel();
    }

    public int diff() {
        return this.req() - this.getProgress();
    }

    public int money() {
        return this.getType().getMoney() * this.getLevel();
    }

    public int exp() {
        return this.getType().getExp() * this.getLevel();
    }

    public String desc() {
        return this.getType().getDescription()
                .replaceAll("%req%", "" + this.req())
                .replaceAll("%prog%", "" + this.getProgress())
                .replaceAll("%diff%", "" + this.diff());
    }

    public boolean isDone() {
        return this.diff() < 1;
    }

    public synchronized void set(Quest type) {
        this.type = type;
        this.progress = 0;
        this.level = new Random().nextInt(1, type.getMaxLvl() + 1);
    }

    public synchronized void clear() {
        this.type = Quest.NONE;
        this.progress = 0;
        this.level = 1;
    }

    public synchronized void progress() {
        this.progress++;
    }
}
