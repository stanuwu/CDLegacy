package com.stanuwu.cdlegacy.game.data;

import com.stanuwu.cdlegacy.game.content.*;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.gameplay.GameScale;
import com.stanuwu.cdlegacy.util.StringUtil;
import com.stanuwu.cdlegacy.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;


public class DBUser {
    private final EventHook event = new EventHook();
    @Getter
    private final long userId;
    @Getter
    private volatile String name;
    @Getter
    @Setter
    private volatile Title title;
    @Getter
    private volatile String description;
    @Getter
    @Setter
    private volatile CDClass cdClass;
    @Getter
    private volatile long exp;
    @Getter
    private volatile long coins;
    @Getter
    @Setter
    private volatile Farming farming;
    @Getter
    private volatile long monstersSlain;
    @Getter
    private volatile long doorsOpened;
    @Getter
    private volatile long bossesSlain;
    @Getter
    private volatile long itemsFound;
    @Getter
    private volatile long chestsOpened;
    @Getter
    private volatile boolean deleted;
    @Getter
    private volatile LocalDateTime lastVote;
    @Getter
    private volatile LocalDateTime lastDoor;
    @Getter
    private volatile LocalDateTime lastTrain;
    @Getter
    private volatile LocalDateTime lastFarm;
    @Getter
    private final DBWeapon weapon;
    @Getter
    private final DBArmor armor;
    @Getter
    private final DBExtra extra;
    @Getter
    private final DBQuest quest;
    @Getter
    private final DBInv inv;

    public DBUser(long userId, String name, String title, String description, String cdClass, long exp, long coins, String farming, long monstersSlain, long doorsOpened, long bossesSlain, long itemsFound, long chestsOpened, boolean deleted, LocalDateTime lastVote, String weapon, String armor, String extra, long weaponExp, long armorExp, long extraExp, String quest, int questLevel, int questProgress, Collection<DBInv.Entry> inv) {
        this.userId = userId;
        this.name = name;
        this.title = DBEnum.fromKey(title, Title.class);
        this.description = description;
        this.cdClass = DBEnum.fromKey(cdClass, CDClass.class);
        this.exp = exp;
        this.coins = coins;
        this.farming = DBEnum.fromKey(farming, Farming.class);
        this.monstersSlain = monstersSlain;
        this.doorsOpened = doorsOpened;
        this.bossesSlain = bossesSlain;
        this.itemsFound = itemsFound;
        this.chestsOpened = chestsOpened;
        this.deleted = deleted;
        this.lastVote = lastVote;
        this.weapon = new DBWeapon(DBEnum.fromKey(weapon, Weapon.class), weaponExp);
        this.armor = new DBArmor(DBEnum.fromKey(armor, Armor.class), armorExp);
        this.extra = new DBExtra(DBEnum.fromKey(extra, Extra.class), extraExp);
        this.quest = new DBQuest(DBEnum.fromKey(quest, Quest.class), questLevel, questProgress);
        this.inv = new DBInv(inv);
        this.lastDoor = TimeUtil.MIN;
        this.lastTrain = TimeUtil.MIN;
        this.lastFarm = TimeUtil.MIN;
    }

    public DBUser(long userId, String name) {
        this.userId = userId;
        this.name = name;
        this.title = Title.PLAYER;
        this.description = "";
        this.cdClass = CDClass.ADVENTURER;
        this.exp = 0;
        this.coins = 50;
        this.farming = Farming.MINING;
        this.monstersSlain = 0;
        this.doorsOpened = 0;
        this.bossesSlain = 0;
        this.itemsFound = 0;
        this.chestsOpened = 0;
        this.deleted = false;
        this.lastVote = TimeUtil.MIN;
        this.weapon = new DBWeapon(Weapon.STICK, 0);
        this.armor = new DBArmor(Armor.RAGS, 0);
        this.extra = new DBExtra(Extra.PENDANT, 0);
        this.quest = new DBQuest(Quest.NONE, 1, 0);
        this.inv = new DBInv();
        this.lastDoor = TimeUtil.MIN;
        this.lastTrain = TimeUtil.MIN;
        this.lastFarm = TimeUtil.MIN;
    }

    public synchronized void setName(String name) {
        this.name = StringUtil.truncate(StringUtil.clean(name), 20);
    }

    public synchronized void setDescription(String description) {
        this.description = StringUtil.truncate(StringUtil.clean(description), 80);
    }

    public boolean is(long id) {
        return this.getUserId() == id;
    }

    public String titleName() {
        return "[%s] %s".formatted(this.getTitle().getTitle(), this.getName());
    }

    public String formatName() {
        return "[%s] [Lvl. %d] %s".formatted(this.getTitle().getTitle(), this.getLevel(), this.getName());
    }

    public String formatLevel() {
        return String.format(Locale.US, "Lvl. %d [Exp. %,d]", this.getLevel(), this.exp);
    }

    public String formatCoins() {
        return String.format(Locale.US, "%,d", this.coins);
    }

    public int getLevel() {
        return GameScale.playerLevel(this.getExp());
    }

    public synchronized void addExp(long exp) {
        this.exp += exp;
    }

    public synchronized void addCoins(long coins) {
        this.coins += coins;
    }

    public synchronized boolean canDoAfford(long coins) {
        if (this.coins < coins) return false;
        this.coins -= coins;
        return true;
    }

    public synchronized void slayMonster() {
        this.monstersSlain++;
    }

    public synchronized void openDoor() {
        this.doorsOpened++;
    }

    public synchronized void slayBoss() {
        this.bossesSlain++;
    }

    public synchronized void findItem() {
        this.itemsFound++;
    }

    public synchronized void openChest() {
        this.chestsOpened++;
    }

    public synchronized void delete() {
        this.deleted = true;
    }

    public synchronized boolean canDoVote(LocalDateTime time) {
        if (TimeUtil.minuteDifference(this.getLastVote(), time) > Cooldown.VOTE.getCd()) {
            this.lastVote = time;
            return true;
        }
        return false;
    }

    public LocalDateTime canVoteAt() {
        return this.getLastVote().plusMinutes(Cooldown.VOTE.getCd() + 1);
    }

    public synchronized boolean canDoDoor(LocalDateTime time) {
        if (TimeUtil.minuteDifference(this.getLastDoor(), time) > Cooldown.DOOR.getCd()) {
            this.lastDoor = time;
            return true;
        }
        return false;
    }

    public LocalDateTime canDoorAt() {
        return this.getLastDoor().plusMinutes(Cooldown.DOOR.getCd() + 1);
    }

    public synchronized boolean canDoTrain(LocalDateTime time) {
        if (TimeUtil.minuteDifference(this.getLastTrain(), time) > Cooldown.TRAIN.getCd()) {
            this.lastTrain = time;
            return true;
        }
        return false;
    }

    public LocalDateTime canTrainAt() {
        return this.getLastTrain().plusMinutes(Cooldown.TRAIN.getCd() + 1);
    }

    public synchronized boolean canDoFarm(LocalDateTime time) {
        if (TimeUtil.minuteDifference(this.getLastFarm(), time) > Cooldown.FARM.getCd()) {
            this.lastFarm = time;
            return true;
        }
        return false;
    }

    public LocalDateTime canFarmAt() {
        return this.getLastFarm().plusMinutes(Cooldown.FARM.getCd() + 1);
    }

    public <T extends Event> void invokeEvent(Events.EventCaller<T> caller, T event) {
        this.event.accept(caller, event);
        this.getWeapon().getType().getEvent().accept(caller, event);
        this.getArmor().getType().getEvent().accept(caller, event);
        this.getExtra().getType().getEvent().accept(caller, event);
        this.getCdClass().getEvent().accept(caller, event);
        this.getQuest().getType().getEvent().accept(caller, event);
    }
}
