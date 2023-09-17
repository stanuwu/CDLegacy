package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.content.Item;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;
import com.stanuwu.cdlegacy.game.event.Ref;

public class EventObtainItem extends Event {
    public final Ref<Item> item;
    public final Ref<Integer> count;

    public EventObtainItem(DBUser user, DBGuild guild, Ref<Item> item, Ref<Integer> count) {
        super(user, guild);
        this.item = item;
        this.count = count;
    }

    @Override
    public void apply() {
        if (!this.isCancelled()) user.getInv().add(item.get(), count.get());
    }
}
