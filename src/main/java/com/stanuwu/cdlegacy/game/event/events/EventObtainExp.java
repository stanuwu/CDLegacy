package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;

public class EventObtainExp extends Event {
    public final Ref<Integer> exp;

    public EventObtainExp(DBUser user, DBGuild guild, Ref<Integer> exp) {
        super(user, guild);
        this.exp = exp;
    }

    @Override
    public void apply() {
        if (!this.isCancelled()) user.addExp(exp.get());
    }
}
