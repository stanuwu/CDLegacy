package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;

public class EventObtainCoins extends Event {
    public final Ref<Integer> coins;

    public EventObtainCoins(DBUser user, DBGuild guild, Ref<Integer> coins) {
        super(user, guild);
        this.coins = coins;
    }

    @Override
    public void apply() {
        if (!this.isCancelled()) user.addCoins(coins.get());
    }
}
