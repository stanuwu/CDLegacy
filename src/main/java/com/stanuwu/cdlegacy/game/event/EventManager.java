package com.stanuwu.cdlegacy.game.event;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@UtilityClass
public class EventManager {
    private final Map<Events.EventCaller<?>, List<Consumer<? extends Event>>> hooks = new HashMap<>();

    public <T extends Event> void register(Events.SingleEventHook<T> hook) {
        if (!hooks.containsKey(hook.caller())) hooks.put(hook.caller(), new ArrayList<>());
        hooks.get(hook.caller()).add(hook.consumer());
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> T call(Events.EventCaller<T> caller, T event) {
        List<Consumer<?>> consumers = (List<Consumer<?>>) hooks.get(caller);
        for (Consumer<?> consumer : consumers) {
            ((Consumer<T>) consumer).accept((event));
        }
        return event;
    }
}
