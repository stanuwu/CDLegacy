package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import org.springframework.lang.Nullable;

public class EventTrain extends Event {
    @Nullable
    public final GearType type;

    public EventTrain(DBUser user, DBGuild guild, @Nullable GearType type) {
        super(user, guild);
        this.type = type;
    }

    @Override
    public void apply() {

    }
}
