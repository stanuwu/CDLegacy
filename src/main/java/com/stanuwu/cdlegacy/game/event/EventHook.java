package com.stanuwu.cdlegacy.game.event;

public class EventHook {
    public static final EventHook NONE = new EventHook();

    public EventHook(Events.SingleEventHook<?>... hooks) {
        for (Events.SingleEventHook<?> hook : hooks) {
            EventManager.register(hook);
        }
    }
}
