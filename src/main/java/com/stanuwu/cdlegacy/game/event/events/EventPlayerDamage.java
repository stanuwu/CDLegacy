package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.gameplay.GameMonster;

public class EventPlayerDamage extends Event {
    public final GameMonster monster;
    public final Ref<Integer> damage;

    public EventPlayerDamage(DBUser user, DBGuild guild, GameMonster monster, Ref<Integer> damage) {
        super(user, guild);
        this.monster = monster;
        this.damage = damage;
    }

    @Override
    public void apply() {

    }
}
