package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.content.Farming;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;

public class EventFarm extends Event {
    public final Farming farming;

    public EventFarm(DBUser user, DBGuild guild, Farming farming) {
        super(user, guild);
        this.farming = farming;
    }

    @Override
    public void apply() {

    }
}
