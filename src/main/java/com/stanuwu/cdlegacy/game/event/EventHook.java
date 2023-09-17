package com.stanuwu.cdlegacy.game.event;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventHook {
    public static final EventHook NONE = new EventHook();
    private final Map<Events.EventCaller<?>, Consumer<?>> events = new HashMap<>();

    public EventHook(Events.SingleEventHook<?>... hooks) {
        for (Events.SingleEventHook<?> hook : hooks) {
            events.put(hook.caller(), hook.consumer());
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> void accept(Events.EventCaller<T> caller, T event) {
        Consumer<T> consumer = (Consumer<T>) events.get(caller);
        if (consumer != null) consumer.accept(event);
    }
}
