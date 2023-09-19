package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

public enum Armor implements IGear {
    RAGS("Rags", "Really dirty and torn apart.", Rarity.COMMON, 0),
    SOCKS("Socks", "Knee-high socks to protect the wearer from dirt and nothing else.", Rarity.COMMON, 1),
    TOP_HAT("Top Hat", "Stylish, but doesn't really shield all that much.", Rarity.COMMON, 2),
    CAPE("Cape", "Makes you look like a superhero, a naked superhero...", Rarity.COMMON, 1),
    HEAVY_PLATED_ARMOR("\"Heavy Plated Armor\"", "Armor made out of paper and painted - no wonder it was so cheap.", Rarity.COMMON, 3),

    CHAINMAIL("Chainmail", "A little rusty, but it does the job.", Rarity.UNCOMMON, 5),
    ROCK_HELMET("Rock Helmet", "Good against concussions.", Rarity.UNCOMMON, 6),
    BRASS_ARMOR("Brass Armor", "This gilden Chestplate is prettier than it's actually useful.", Rarity.UNCOMMON, 6),
    HARDENED_CLAY_ARMOR("Hardened Clay Armor", "A layer burnt onto your body in exactly the right shape.", Rarity.UNCOMMON, 7),

    THIN_CRYSTAL_ARMOR("Thin Crystal Armor", "Shiny AND quite sturdy!", Rarity.RARE, 15),
    VOLCANO_ROCK_CUIRASS("Volcano Rock Cuirass", "Made of volcanic rocks, lava veins are still visible.\nHot Touch: Burns the enemy.", Rarity.RARE, 10, new EventHook(
            Events.MONSTER_HEALTH.make(e -> e.health.op(h -> (int) (h * 0.95f)))
    )),
    DragonCrown("Dragon Crown", "The horns of a young dragon.\nStrength: Increases your base damage.", Rarity.RARE, 12, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> d + 15))
    )),
    ARCHERS_VEST("Archer's Vest", "Makes you swift, but lacking in defense.\nArcher's Blessing: Bows deal increased damage.", Rarity.RARE, 10, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType() == DamageType.BOW ? (int) (d * 1.15f) : d))
    )),
    FOGGLASS_GUARD("Fogglass Guard", "Vanish in the fog.", Rarity.RARE, 14);
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Rarity rarity;
    @Getter
    private final int reduction;
    @Getter
    private final EventHook event;

    Armor(String name, String description, Rarity rarity, int reduction, EventHook event) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.reduction = reduction;
        this.event = event;
    }

    Armor(String name, String description, Rarity rarity, int reduction) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.reduction = reduction;
        this.event = EventHook.NONE;
    }
}
