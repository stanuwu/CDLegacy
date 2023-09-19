package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.gameplay.GameMonster;

public class EventPlayerBlock extends Event {
    public final GameMonster monster;
    public final Ref<Float> block;

    public EventPlayerBlock(DBUser user, DBGuild guild, GameMonster monster, Ref<Float> block) {
        super(user, guild);
        this.monster = monster;
        this.block = block;
    }

    @Override
    public void apply() {

    }
}
