package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;

public class EventPlayerDamage extends Event {
    public final Ref<Integer> damage;

    public EventPlayerDamage(DBUser user, DBGuild guild, Ref<Integer> damage) {
        super(user, guild);
        this.damage = damage;
    }

    @Override
    public void apply() {

    }
}
