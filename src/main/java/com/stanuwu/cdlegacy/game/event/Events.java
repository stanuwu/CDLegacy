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
    public final EventCaller<EventFarm> FARM = new EventCaller<>();
    public final EventCaller<EventTrain> TRAIN = new EventCaller<>();
    public final EventCaller<EventFindChest> FIND_CHEST = new EventCaller<>();
    public final EventCaller<EventOpenDoor> OPEN_DOOR = new EventCaller<>();
    public final EventCaller<EventGearDrop> GEAR_DROP = new EventCaller<>();
    public final EventCaller<EventMonsterBlock> MONSTER_BLOCK = new EventCaller<>();
    public final EventCaller<EventMonsterDamage> MONSTER_DAMAGE = new EventCaller<>();
    public final EventCaller<EventMonsterHealth> MONSTER_HEALTH = new EventCaller<>();
    public final EventCaller<EventPlayerBlock> PLAYER_BLOCK = new EventCaller<>();
    public final EventCaller<EventPlayerDamage> PLAYER_DAMAGE = new EventCaller<>();
    public final EventCaller<EventPlayerHealth> PLAYER_HEALTH = new EventCaller<>();

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
