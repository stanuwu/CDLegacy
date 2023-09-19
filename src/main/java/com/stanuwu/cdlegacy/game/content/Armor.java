package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

import java.util.Random;

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
    FOGGLASS_GUARD("Fogglass Guard", "Vanish in the fog.", Rarity.RARE, 14),

    GOLEM_SKIN("Golem Skin", "Made from the body of a rock creature.", Rarity.EPIC, 18),
    BONE_ARMOR("Bone Armor", "An armor-set made of skeleton bones.", Rarity.EPIC, 16),
    MOTH_PELT_WEST("Moth Pelt Vest", "Makes you feel attracted to light.", Rarity.EPIC, 17),
    DEEPFOREST_CLOAK("Deepforest Cloak", "Protective, but still meant for mobility. Comes with healing herbs.\nHealthy Herbs: Increases your health.", Rarity.EPIC, 14, new EventHook(
            Events.PLAYER_HEALTH.make(e -> e.health.op(h -> (int) (h * 1.1f)))
    )),
    AQUATIC_ROBE("Aquatic Robe", "Cloak yourself in flowing waters.\nCurrent: Increases your damage but decreases your block.", Rarity.EPIC, 15, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(f -> (int) (f * 1.2f))),
            Events.PLAYER_BLOCK.make(e -> e.block.op(f -> f * 0.8f))
    )),

    BERSERKERS_GUARD("Berserker's Guard", "Focus fully on fighting with little regard for your survival.\nBerserker's Fury: Halves your health, but significantly increases damage.", Rarity.LEGENDARY, 25, new EventHook(
            Events.PLAYER_HEALTH.make(e -> e.health.op(h -> (int) (h * 0.5f))),
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(f -> (int) (f * 1.3f)))
    )),
    MASK_OF_THE_PURVEYOR("Mask of the Purveyor", "Spread the truth you believe in.", Rarity.LEGENDARY, 26),
    DIAMOND_DUST_CUIRASS("Diamond Dust Cuirass", "You'll freeze a little bit, but it's worth the good defense.\nChilly: Freezes you, dealing slight damage.", Rarity.LEGENDARY, 29, new EventHook(
            Events.PLAYER_HEALTH.make(e -> e.health.op(h -> h - 30))
    )),
    SAGES_ROBE("Sage's Robe", "Really comfy. Those old magic users sure have it nice.\nIncreases magic type damage.", Rarity.LEGENDARY, 22, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType().getArchtype() == DamageArchtype.MAGIC ? (int) (d * 1.2f) : d))
    )),
    GOLDWITCH_OVERCOAT("Goldwitch Overcoat", "Enchanted by witches, this piece of armor is sure to bring you financial luck.\nMoneymaker: Increases coins gained.", Rarity.LEGENDARY, 22, new EventHook(
            Events.OBTAIN_COINS.make(e -> e.coins.op(c -> (int) (c * 1.1f)))
    )),

    GAMBLERS_RUIN("Gambler's Ruin", "It's all or nothing.\nLethal Gamble: 50% Chance to fully negate damage.", Rarity.UNSTABLE, 0, new EventHook(
            Events.PLAYER_BLOCK.make(e -> e.block.set(new Random().nextBoolean() ? 0f : 1f))
    )),
    ERROR("CustomDungeons.Weapon.Name.Null", "CustomDungeons.Weapon.Description.Null\nSystem Fault: Randomized block and damage.", Rarity.UNSTABLE, 0, new EventHook(
            Events.PLAYER_BLOCK.make(e -> e.block.set(new Random().nextFloat(0, 1))),
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> (int) (d * new Random().nextFloat(0, 2))))
    )),
    PIMA_LONGCOAT("Pima Longcoat", "The fluffiest, most comfortable piece of clothing around.", Rarity.UNSTABLE, 32),
    BLAZING_FIREGUARD("Blazing Fireguard", "The flames of this chestplate keep foes at bay.", Rarity.UNSTABLE, 31),
    MECHA_SUIT("Mecha Suit", "Technological advancements allow you to enhance yourself with this robotic suit. Prone to malfunction.\nSafety Hazard: Chance to hurt you when fighting.", Rarity.UNSTABLE, 36, new EventHook(
            Events.PLAYER_HEALTH.make(e -> e.health.op(h -> new Random().nextInt(0, 6) == 0 ? h - 150 : h))
    )),

    FIBRE_OF_HATRED("Fibre of Hatred", "Focus on hurting your foes rather than defending.\nPower of Hatred: Significantly increases your damage.", Rarity.CORRUPTED, 25, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> (int) (d * 1.30f)))
    )),
    CORE_SMELTER_ARMOR("Core Smelter Armor", "Made of the innermost layer of earth.\nCore's Heat: Slightly increases your damage.", Rarity.CORRUPTED, 35, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> d + 25))
    )),
    WYVERNS_AEGIS("Wyvern's Aegis", "Able to withstand even a meteor strike.", Rarity.CORRUPTED, 45),
    ADAPTIVE_BIOARMOR("Adaptive Bioarmor", "Hi-tech, adapts to every situation that comes your way.\nModifier: Increases your damage if your weapon and extra are of the same rarity.", Rarity.CORRUPTED, 25, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getRarity() == e.user.getExtra().getType().getRarity() ? (int) (d * 1.35) : d))
    )),
    SYLPHID_WINGS("Sylphid Wings", "Once belonging to a wind elemental, you can now freely fly with them.\nSylph's Flight: Dodges every other attack.", Rarity.CORRUPTED, 25, new EventHook(
            Events.MONSTER_DAMAGE.make(e -> e.damage.op(d -> (int) (d / 2f)))
    )),

    FISH_SCALE_MAIL("Fish Scale Mail", "Really shiny, not sturdy.", Rarity.CRAFTED, 1),
    GOLD_PLATED_ARMOR("Gold Plated Armor", "Quite hefty - good defense, but you look sort of stupid with it.", Rarity.CRAFTED, 11),
    HYDRA_SCALE_MAIL("Hydra Scale Mail", "Not as fishy as that other set of armor.", Rarity.CRAFTED, 25),

    WORLD_ARMOR("World Armor", "This Armor is forged from the pieces of space-time.", Rarity.UNIQUE, 60),
    ;

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
