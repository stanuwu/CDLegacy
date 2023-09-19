package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

import java.util.Random;

public enum Weapon implements IGear {
    STICK("Wooden Stick", "Could be used to hit things.", Rarity.COMMON, 3, DamageType.BLUNT),
    STONE_HATCHET("Stone Hatchet", "A tool generally used for cutting wood.", Rarity.COMMON, 7, DamageType.AXE),
    SHORTSWORD("Shortsword", "A basic blade crafted with simple iron.", Rarity.COMMON, 8, DamageType.SWORD),
    WOODEN_WAND("Wooden Wand", "Equipped to cast only the most basic of spells.", Rarity.COMMON, 7, DamageType.WAND),
    DAGGER("Dagger", "A small dagger that can be hidden away in your sleeve.", Rarity.COMMON, 6, DamageType.DAGGER),
    CLAYMORE("Claymore", "A large but heavy blade.", Rarity.COMMON, 11, DamageType.SWORD),
    MINIATURE_LANCE("Miniature Lance", "A shortened lance for short range stabbing.", Rarity.COMMON, 8, DamageType.LANCE),

    BLADED_BOOMERANG("Bladed Boomerang", "Sharp and throwable.", Rarity.UNCOMMON, 19, DamageType.BOOMERANG),
    HUNTING_BOW("Hunting Bow", "Shoots powerful steel arrows.", Rarity.UNCOMMON, 17, DamageType.BOW),
    MANA_CATALYST("Mana Catalyst", "Absorbs the mana around you and channels it into magic.", Rarity.UNCOMMON, 16, DamageType.SIGIL),
    DREIHANDER("Dreihander", "A sword so heavy that two hands aren't enough to carry it.", Rarity.UNCOMMON, 18, DamageType.SWORD),
    BIG_CANNON("Big Cannon", "Watch out for the recoil.", Rarity.UNCOMMON, 17, DamageType.FIREARM),
    MORNING_STAR("Morning Star", "A big spiky ball on a stick.", Rarity.UNCOMMON, 21, DamageType.BLUNT),

    VAMPIRE_TOOTH("Vampire Tooth", "Steal your opponents' life by slashing them apart.\nLifesteal: Overheal by a fraction of your opponents health.", Rarity.RARE, 25, DamageType.DAGGER, new EventHook(
            Events.PLAYER_HEALTH.make(e -> e.health.op(h -> h + (e.monster.getHealth() / 10)))
    )),
    DARKENED_LUCERNE("Darkened Lucerne", "The blood on this halberd has turned its color to a dark red.", Rarity.RARE, 31, DamageType.LANCE),
    SPIKED_BRASS_KNUCKLES("Spiked Brass Knuckles", "Punch your enemy into the ground, leaving no room for parries.", Rarity.RARE, 29, DamageType.BLUNT),
    KUSARIGAMA("Kusarigama", "A chain sickle to wrap and slice your enemies up with.", Rarity.RARE, 29, DamageType.SICKLE),
    FLAMING_SCIMITAR("Flaming Scimitar", "This lightweight, curved blade burns your enemies with a slash.", Rarity.RARE, 30, DamageType.SWORD),
    ELECTRIC_CROSSBOW("Electric Crossbow", "Shoots electrically-charged, hefty bolts.", Rarity.RARE, 27, DamageType.BOW),

    CRYSTALREND("Crystalrend", "The reflection of their faces on this gleaming blade is the last thing the enemy'll see.", Rarity.EPIC, 48, DamageType.SWORD),
    STAFF_OF_THE_ELEMENTS("Staff of the Elements", "Channel the eternal strength of nature and force it upon your foes.", Rarity.EPIC, 47, DamageType.WAND),
    FUTUREFINDER("Futurefinder", "A blade that is said to come from a time succeeding ours.\nFuture Sight: Has a chance to predict an enemy's attack, completely avoiding it.", Rarity.EPIC, 33, DamageType.SWORD, new EventHook(
            Events.MONSTER_DAMAGE.make(e -> e.damage.op(d -> new Random().nextInt(0, 11) == 0 ? 0 : d))
    )),
    LANCE_OF_THE_STARS("Lance of the Stars", "All the stars' fury gets cast upon an opponent pierced by this weapon.", Rarity.EPIC, 49, DamageType.LANCE),
    FROSTBITE_TOME("Frostbite Tome", "Etches the arts of ice magic into your soul.\nFreeze: Decreases enemy damage and block.", Rarity.EPIC, 44, DamageType.WAND, new EventHook(
            Events.MONSTER_DAMAGE.make(e -> e.damage.op(d -> (int) (d * 0.95f))),
            Events.MONSTER_BLOCK.make(e -> e.block.op(b -> b * 0.95f))
    )),
    LONG_SUNKEN_ANCHOR("Long-sunken Anchor", "Reclaimed by oceanic wildlife, but still hits like a truck.", Rarity.EPIC, 48, DamageType.BLUNT),
    DARKWOOD_STAFF("Darkwood Staff", "You can feel the forest's power flowing through it with every hit.", Rarity.EPIC, 47, DamageType.WAND),

    LINK_BREAKER("Link Breaker", "No trace of a connection remains once something is cut by this blade.", Rarity.LEGENDARY, 75, DamageType.SWORD),
    ANCIENT_ROOT_HAMMER("Ancient Root Hammer", "Birthed from an ancient, evergrowing tree, this hammer is nearly indestructible.\nStun: Decreases enemy block.", Rarity.LEGENDARY, 71, DamageType.BLUNT, new EventHook(
            Events.MONSTER_BLOCK.make(e -> e.block.op(b -> b * 0.9f))
    )),
    RUNED_GREATSHIELD("Runed Greatshield", "A gigantic shield to protect you from attacks - or bash in some heads.\nFortified: Increases your block.", Rarity.LEGENDARY, 69, DamageType.BLUNT, new EventHook(
            Events.PLAYER_BLOCK.make(e -> e.block.op(b -> b * 1.1f))
    )),
    EREBUS_GAUNTLETS("Erebus Gauntlets", "Gauntlets imbued with the power of the underworld.\nCritical Eye: Chance to deal critical damage.", Rarity.LEGENDARY, 73, DamageType.BLUNT, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> (int) (d * (new Random().nextInt(0, 11) == 0 ? 1.15f : 1f))))
    )),
    LIGHTRAY_BOWGUN("Lightray Bowgun", "Beam your enemies away with the sun's shining light.\nBlessing Light: Heals you on attack.", Rarity.LEGENDARY, 74, DamageType.BOW, new EventHook(
            Events.PLAYER_HEALTH.make(e -> e.health.op(h -> (int) (h * 1.1f)))
    )),
    PROPELLER_BLADE("Propeller Blade", "An ancient artifact, from civilisations that once were.\nMotor Function: Damage fluctuates randomly.", Rarity.LEGENDARY, 75, DamageType.SWORD, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> (int) ((d / 0.5f) * new Random().nextInt(0, 4))))
    )),
    SCYTHE_OF_THE_YOUNG_REAPER("Scythe of the Young Reaper", "Used to take away the souls of the deceased - but doesn't always work.\nGuide to the Afterlife: Small chance to instantly kill an enemy.", Rarity.LEGENDARY, 20, DamageType.SCYTHE, new EventHook(
            Events.MONSTER_HEALTH.make(e -> e.health.op(h -> new Random().nextInt(0, 11) == 0 ? 0 : h))
    )),

    PULSAR_BREAKER("Pulsar Breaker", "Can break a star in half with magnetic powers.", Rarity.UNSTABLE, 94, DamageType.SIGIL),
    SOULTRACER("Soultracer", "Slice the edges of your opponent's soul.\nCritical Eye: Chance to deal critical damage.", Rarity.UNSTABLE, 90, DamageType.SWORD, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> (int) (d * (new Random().nextInt(0, 11) == 0 ? 1.15f : 1f))))
    )),
    HEARTSTOPPER("Heartstopper", "These claws are infamous for making opponents freeze in fear merely at a glance.\nWeakness Exploit: Greatly decreases enemy block.", Rarity.UNSTABLE, 90, DamageType.BLUNT, new EventHook(
            Events.MONSTER_BLOCK.make(e -> e.block.op(b -> b * 0.85f))
    )),
    BODYBREAKER("Bodybreaker", "Pulverize your opponent's bones through mere punches.\nStun: Decreases enemy block.", Rarity.UNSTABLE, 90, DamageType.BLUNT, new EventHook(
            Events.MONSTER_BLOCK.make(e -> e.block.op(b -> b * 0.9f))
    )),
    DEEP_OCEAN_GEYSER("Deep Ocean Geyser", "Command the ancient water spirits and blast your foes.", Rarity.UNSTABLE, 97, DamageType.SPIRIT),
    EBONY_CLOUD("Ebony Cloud", "Through ancient spirits thunder and lightning are yours to control.", Rarity.UNSTABLE, 95, DamageType.SPIRIT),
    SPHERE_OF_THE_ABYSS("Sphere of the Abyss", "Absorbs the light to bring about eternal darkness.", Rarity.UNSTABLE, 98, DamageType.WAND),

    NOCTURNAL_SCYTHE("Nocturnal Scythe", "Can taint the entire sky a deep, abyssal dark.", Rarity.CORRUPTED, 130, DamageType.SCYTHE),
    SYZYGY("Syzygy", "A pair of celestial swords, the sun and the moon, aligning with your very being.", Rarity.CORRUPTED, 126, DamageType.SWORD),
    SCEPTER_OF_CORES("Scepter of Cores", "The inferno of the planet's core is at your disposal.", Rarity.CORRUPTED, 120, DamageType.WAND),
    DOGMA("Dogma", "Gauntlets of gleaming plasma with destructive capabilities.\nHeavy Stun: Greatly reduce enemy block and damage.", Rarity.CORRUPTED, 110, DamageType.BLUNT, new EventHook(
            Events.MONSTER_DAMAGE.make(e -> e.damage.op(d -> (int) (d * 0.90f))),
            Events.MONSTER_BLOCK.make(e -> e.block.op(b -> b * 0.90f))
    )),
    HAMMER_OF_TABULA_RASA("Hammer of Tabula Rasa", "Said to be able to flatten mountains.", Rarity.CORRUPTED, 122, DamageType.BLUNT),
    ASTRAL_BINDING_BOW("Astral Binding Bow", "Bound to the stars, this weapon can light up the night sky.", Rarity.CORRUPTED, 124, DamageType.BOW),
    JORMUNGANDR("Jormungandr", "A giant longsword, unmatched in length, and an undying snake wrapped around its blade.\nWorldpoison: Poisons your enemy, reducing their health.", Rarity.CORRUPTED, 121, DamageType.SWORD, new EventHook(
            Events.MONSTER_HEALTH.make(e -> e.health.op(h -> (int) (h * 0.9f)))
    )),

    WOODEN_SWORD("Wooden Sword", "Only made for combat training.", Rarity.CRAFTED, 5, DamageType.SWORD),
    WOLF_BLADE("Wolf Blade", "A small dagger, formed from a wolf tooth.", Rarity.CRAFTED, 13, DamageType.DAGGER),
    DIAMOND_TIP_SWORD("Diamond Tip Sword", "Made out of an incredible amount of stone and one diamond.", Rarity.CRAFTED, 50, DamageType.SWORD),

    LIFE_EXTRACTOR("Life Extractor",
            "This Blade has taken many lives, once owned my a mighty Life Collector it allows you to take other peoples lives as well.", Rarity.UNIQUE, 220, DamageType.SWORD),
    ;

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Rarity rarity;
    @Getter
    private final int damage;
    @Getter
    private final DamageType damageType;
    @Getter
    private final EventHook event;

    Weapon(String name, String description, Rarity rarity, int damage, DamageType damageType, EventHook event) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.damage = damage;
        this.damageType = damageType;
        this.event = event;
    }

    Weapon(String name, String description, Rarity rarity, int damage, DamageType damageType) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.damage = damage;
        this.damageType = damageType;
        this.event = EventHook.NONE;
    }

    public String formatType() {
        return this.getDamageType().getName() + " (" + this.getDamageType().getArchtype().getName() + ")" ;
    }
}
