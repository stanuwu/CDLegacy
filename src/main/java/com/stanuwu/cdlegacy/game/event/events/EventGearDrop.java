package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.content.IGear;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;

public class EventGearDrop extends Event {
    public final IGear gear;

    public EventGearDrop(DBUser user, DBGuild guild, IGear gear) {
        super(user, guild);
        this.gear = gear;
    }

    @Override
    public void apply() {
        this.user.findItem();
    }
}
