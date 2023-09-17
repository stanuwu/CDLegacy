package com.stanuwu.cdlegacy.game.event;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import lombok.Getter;
import lombok.Setter;

public abstract class Event {
    @Getter
    @Setter
    private boolean cancelled;

    public abstract void apply(DBUser user, DBGuild guild);
}
