package com.stanuwu.cdlegacy.game.event.events;

import com.stanuwu.cdlegacy.game.content.Item;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Event;

public class EventObtainItem extends Event {
    public Item item;
    public long count;

    public EventObtainItem(Item item, long count) {
        this.item = item;
        this.count = count;
    }
    
    @Override
    public void apply(DBUser user, DBGuild guild) {
        if (!this.isCancelled()) user.getInv().add(item, count);
    }
}
