package com.stanuwu.cdlegacy.game.event;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.events.EventObtainItem;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

import java.util.function.Consumer;

@UtilityClass
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class Events {
    EventCaller<EventObtainItem> OBTAIN_ITEM = new EventCaller<>();

    public static class EventCaller<T extends Event> {
        public void invoke(T event, DBUser user, DBGuild guild) {
            EventManager.call(this, event).apply(user, guild);
        }

        public SingleEventHook<T> hook(Consumer<T> consumer) {
            return new SingleEventHook<>(this, consumer);
        }
    }

    public record SingleEventHook<T extends Event>(EventCaller<T> caller, Consumer<T> consumer) {

    }
}
