package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;

public class EventMonsterHealth extends Event {
    public final Ref<Integer> health;

    public EventMonsterHealth(DBUser user, DBGuild guild, Ref<Integer> health) {
        super(user, guild);
        this.health = health;
    }

    @Override
    public void apply() {

    }
}
