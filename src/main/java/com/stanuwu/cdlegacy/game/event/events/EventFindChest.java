package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;

public class EventFindChest extends Event {
    public EventFindChest(DBUser user, DBGuild guild) {
        super(user, guild);
    }

    @Override
    public void apply() {
        this.user.openChest();
    }
}
