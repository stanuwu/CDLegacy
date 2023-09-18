package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;

public class EventPlayerBlock extends Event {
    public final Ref<Float> block;

    public EventPlayerBlock(DBUser user, DBGuild guild, Ref<Float> block) {
        super(user, guild);
        this.block = block;
    }

    @Override
    public void apply() {

    }
}
