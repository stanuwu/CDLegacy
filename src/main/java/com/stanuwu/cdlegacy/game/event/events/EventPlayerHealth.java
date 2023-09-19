package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.gameplay.GameMonster;

public class EventPlayerHealth extends Event {
    public final GameMonster monster;
    public final Ref<Integer> health;

    public EventPlayerHealth(DBUser user, DBGuild guild, GameMonster monster, Ref<Integer> health) {
        super(user, guild);
        this.monster = monster;
        this.health = health;
    }

    @Override
    public void apply() {

    }
}
