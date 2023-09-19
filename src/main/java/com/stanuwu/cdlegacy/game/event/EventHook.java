package com.stanuwu.cdlegacy.game.event;

import com.stanuwu.cdlegacy.game.content.DamageType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventHook {
    public static final EventHook NONE = new EventHook();

    public static Events.SingleEventHook<?> damage(float multiplier) {
        return Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> (int) (d * multiplier)));
    }

    public static Events.SingleEventHook<?> health(float multiplier) {
        return Events.PLAYER_HEALTH.make(e -> e.health.op(h -> (int) (h * multiplier)));
    }

    public static Events.SingleEventHook<?> block(float multiplier) {
        return Events.PLAYER_BLOCK.make(e -> e.block.op(b -> b * multiplier));
    }

    public static Events.SingleEventHook<?> weaponDamage(DamageType type, float multiplier) {
        return Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType() == type ? (int) (d * multiplier) : d));
    }

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
