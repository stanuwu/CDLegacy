package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.content.Weapon;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;

public class EventObtainExpGear extends Event {
    public final Ref<Integer> exp;
    public final Ref<GearType> type;

    public EventObtainExpGear(DBUser user, DBGuild guild, Ref<Integer> exp, Ref<GearType> type) {
        super(user, guild);
        this.exp = exp;
        this.type = type;
    }

    @Override
    public void apply() {
        if (!this.isCancelled()) type.get().addExp(user, exp.get());
    }
}
