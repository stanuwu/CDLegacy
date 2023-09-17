package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;

public class EventSlayMonster extends Event {
    // Monster

    public EventSlayMonster(DBUser user, DBGuild guild) {
        super(user, guild);
    }

    @Override
    public void apply() {
        user.slayMonster();
        guild.slayMonster();
    }
}
