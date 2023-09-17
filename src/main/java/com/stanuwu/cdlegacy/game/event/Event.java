package com.stanuwu.cdlegacy.game.event;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import lombok.Getter;
import lombok.Setter;

public abstract class Event {
    @Getter
    @Setter
    private boolean cancelled;

    public final DBUser user;
    public final DBGuild guild;

    protected Event(DBUser user, DBGuild guild) {
        this.user = user;
        this.guild = guild;
    }

    public abstract void apply();
}
