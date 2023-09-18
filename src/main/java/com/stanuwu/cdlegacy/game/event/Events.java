package com.stanuwu.cdlegacy.game.event;

import com.stanuwu.cdlegacy.game.event.events.*;
import lombok.experimental.UtilityClass;

import java.util.function.Consumer;

@UtilityClass
public class Events {
    public final EventCaller<EventObtainCoins> OBTAIN_COINS = new EventCaller<>();
    public final EventCaller<EventObtainExp> OBTAIN_EXP = new EventCaller<>();
    public final EventCaller<EventObtainExpGear> OBTAIN_EXP_GEAR = new EventCaller<>();
    public final EventCaller<EventObtainItem> OBTAIN_ITEM = new EventCaller<>();
    public final EventCaller<EventSlayMonster> SLAY_MONSTER = new EventCaller<>();

    public static class EventCaller<T extends Event> {
        public void invoke(T event) {
            event.user.invokeEvent(this, event);
            event.guild.invokeEvent(this, event);
            event.apply();
        }

        public SingleEventHook<T> make(Consumer<T> consumer) {
            return new SingleEventHook<>(this, consumer);
        }
    }

    public record SingleEventHook<T extends Event>(EventCaller<T> caller, Consumer<T> consumer) {
    }
}
