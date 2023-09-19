package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

public enum Extra implements IGear {
    PENDANT("Pendant", "A small, ochre amulet.", Rarity.COMMON),
    FIREFLY("Firefly", "Little insect buddy that seems to follow you wherever you go.\nBuffs Health.", Rarity.COMMON, new EventHook(
            EventHook.health(1.02f)
    )),
    SCARF("Scarf", "This classy piece of fabric makes you stronger purely by proxy of feeling cooler with it.\nBuffs Damage.", Rarity.COMMON, new EventHook(
            EventHook.damage(1.02f)
    )),
    WATER_FLASK("Water Flask", "Refreshes the soul just enough to let you fight a little longer.\nBuffs Block.", Rarity.COMMON, new EventHook(
            EventHook.block(1.02f)
    )),
    MAGIC_POWDER("Magic Powder", "Just a few grains of this can enhance your physical ability beyond normal levels. About 1% beyond normal levels.\nBuffs Damage and Block.", Rarity.COMMON, new EventHook(
            EventHook.damage(1.01f),
            EventHook.block(1.01f)
    )),
    TINY_HORN("Tiny Horn", "Its sound prepares you for battle.\nBuffs Damage.", Rarity.COMMON, new EventHook(
            EventHook.damage(1.02f)
    )),
    MEMORY_FRAGMENT("Memory Fragment", "A little bit of knowledge on how to fight, hidden in your own mind.\nBuffs Damage.", Rarity.COMMON, new EventHook(
            EventHook.damage(1.02f)
    )),

    BLADE_SHARPENER("Blade Sharpener", "Gives you that little edge.\nBuffs Damage.", Rarity.UNCOMMON, new EventHook(
            EventHook.damage(1.05f)
    )),
    TURBO_SHROOM("Turbo Shroom", "Tastes horrible, but it's healthy!\nBuffs Health.", Rarity.UNCOMMON, new EventHook(
            EventHook.health(1.05f)
    )),
    HONEYBEE("Honeybee", "Brings you some refreshing honey every so often.\nBuffs Damage.", Rarity.UNCOMMON, new EventHook(
            EventHook.damage(1.05f)
    )),
    BUCKLER("Buckler", "A small shield, only meant for quick parries.\nBuffs Block.", Rarity.UNCOMMON, new EventHook(
            EventHook.block(1.05f)
    )),
    MIRROR("Mirror", "Blind your enemies, giving you more time to plan your next move.\nBuffs Damage.", Rarity.UNCOMMON, new EventHook(
            EventHook.damage(1.05f)
    )),
    MODULAR_HOLSTER("Modular Holster", "A little contraption that allows you to pull your weapon faster than ever.\nBuffs Damage.", Rarity.UNCOMMON, new EventHook(
            EventHook.damage(1.05f)
    )),

    WAND_OF_HEALING("Wand of Healing", "Allows you to access your intrinsic healing abilities.\nBuffs Health,", Rarity.RARE, new EventHook(
            EventHook.health(1.1f)
    )),
    WAND_OF_STRENGTH("Wand of Strength", "Magically boosts your fighting prowess.\nBuffs Damage.", Rarity.RARE, new EventHook(
            EventHook.damage(1.1f)
    )),
    WAND_OF_SHIELDING("Wand of Shielding", "Makes your skin just a bit harder.\nBuffs Block.", Rarity.RARE, new EventHook(
            EventHook.block(1.1f)
    )),
    WAND_OF_BALANCE("Wand of Balance", "Every part of you feels stronger... but only slightly.\nBuffs Health, Damage and Block.", Rarity.RARE, new EventHook(
            EventHook.health(1.05f),
            EventHook.damage(1.05f),
            EventHook.block(1.05f)
    )),
    BLACK_CAT("Black Cat", "A small familiar that helps you in every fight.\nMeow: Increases spirit damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.SPIRIT, 1.1f)
    )),
    CASTER_AMULET("Caster Amulet", "A pendant imbued with powerful sorcery.\nSuch Sorcery: Increases wand damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.WAND, 1.1f)
    )),
    FRIENDLY_WISP("Friendly Wisp", "Keeps flying around you and making little noises. Kind of cute.\nBuffs Damage.", Rarity.RARE, new EventHook(
            EventHook.damage(1.11f)
    )),
    GILDED_SHEATH("Gilded Sheath", "A fancy sheath for your sword.\nSafe and Sharp: Increases sword damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.SWORD, 1.2f)
    )),
    GILDED_QUIVER("Gilded Quiver", "A fancy quiver for your arrows.\nQuickshot: Increases bow damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.BOW, 1.2f)
    )),
    THIEF_HOOD("Thief Robes", "A black hood, one would wear at night when stealing.\nSneaky: Increases dagger damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.DAGGER, 1.2f)
    )),
    CHOPPING_BLOCK("Chopping Block", "Let's you use your skills the right way.\nBig Lumber: Increases axe damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.AXE, 1.2f)
    )),
    ALCOHOLIC_BEVERAGE("Alcoholic Beverage", "Throws you into a blind fit of rage.\nDrunk Smasher: Increases blunt damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.BLUNT, 1.2f)
    )),
    HOBBYHORSE("Hobbyhorse", "At least you can pretend...\nHorseback: Increases lance damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.BLUNT, 1.2f)
    )),
    RED_ROCK("Red Rock", "This might once have been part of a big holy rock.\nAussie: Increases boomerang damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.BOOMERANG, 1.2f)
    )),
    MANA_GEM("Mana Gem", "A pretty gem, that can store magic power.\nDischarge: Increases sigil damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.SIGIL, 1.2f)
    )),
    AIMING_PRISM("Aiming Prism", "May the light show you the lethal spot.\nAim Assist: Increases firearm damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.FIREARM, 1.2f)
    )),
    GOLDEN_WHEAT("Golden Wheat", "Just a normal piece of wheat in the sunlight. It reminds you of home.\nField Advantage: Increases sickle damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.SICKLE, 1.2f)
    )),
    GRIM_AURA("Grim Aura", "Hey, if you are tired just say so...\nSleeper Reaper: Increases scythe damage.", Rarity.RARE, new EventHook(
            EventHook.weaponDamage(DamageType.SCYTHE, 1.2f)
    )),

    ROYAL_EMBLEM("Royal Emblem", "A certain royal family's crest - wearing it makes people think you're important.\nCorruption: Increases coins gained.", Rarity.EPIC, new EventHook(
            Events.OBTAIN_COINS.make(e -> e.coins.op(c -> (int) (c * 1.1f)))
    )),
    TORTUGA_TALISMAN("Tortuga Talisman", "Made of a hard, nearly indestructible shell, this charm saves you from harm.\nTurtle Spirit: Increases your block.", Rarity.EPIC, new EventHook(
            EventHook.block(1.15f)
    )),
    AMBROSIA("Ambrosia", "A meal of gods, giving you incredible health.\nNutrients: Increases your health.", Rarity.EPIC, new EventHook(
            EventHook.health(1.15f)
    )),
    SIRENS_SONG("Siren's Song", "Heed the call of the sirens.\nScreech: Decreases enemy damage.", Rarity.EPIC, new EventHook(
            Events.MONSTER_DAMAGE.make(e -> e.damage.op(d -> (int) (d * 0.85f)))
    )),
    DREAM_CATCHER("Dream Catcher", "Casts the evil thoughts gathered in your sleep unto your enemies.\nNightmare: Increases your damage.", Rarity.EPIC, new EventHook(
            EventHook.damage(1.15f)
    )),
    PETRIFIED_LEAF("Petrified Leaf", "You can still see a little of its auburn color.\nBygones: Increases your damage and block.", Rarity.EPIC, new EventHook(
            EventHook.damage(1.1f),
            EventHook.block(1.1f)
    )),

    BOOK_OF_LEGENDS("Book of Legends", "Contains a story about a certain fiery orb.\nKnowledge is Power: Increases your exp gained.", Rarity.LEGENDARY, new EventHook(
            Events.OBTAIN_EXP.make(e -> e.exp.op(ex -> (int) (ex * 1.2f)))
    )),
    GRAPPLING_HOOK("Grappling Hook", "Close in on enemies faster, or make them close in on you. Your choice.\nTraversal: Increases far range type attack damage.", Rarity.LEGENDARY, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType().getArchtype() == DamageArchtype.PHYSICAL_FAR ? (int) (d * 1.2f) : d))
    )),
    PUMPED_UP_KICKS("Pumped up Kicks", "Get a kick in with these stylish sneakers.\nBully: Increases close range type attack damage.", Rarity.LEGENDARY, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType().getArchtype() == DamageArchtype.PHYSICAL_CLOSE ? (int) (d * 1.2f) : d))
    )),
    MOON_REFRACTOR("Moon Refractor", "Absorb the moon's light and utilize its might.\nDarkest Light: Increases magic type attack damage.", Rarity.LEGENDARY, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType().getArchtype() == DamageArchtype.MAGIC ? (int) (d * 1.2f) : d))
    )),
    SLIVER_OF_DARKNESS("Sliver of Darkness", "A shard of unknown origin, said to be made of pure, deep darkness.\nLife Decrease: Decreases enemy health.", Rarity.LEGENDARY, new EventHook(
            Events.MONSTER_HEALTH.make(e -> e.health.op(h -> h - 150))
    )),
    MAGNIFICENT_WISH("Magnificent Wish", "A mystical wish for salvation embedded deeply into you.\nEnd of the Tunnel: Significantly increases your damage.", Rarity.LEGENDARY, new EventHook(
            EventHook.damage(1.25f)
    )),
    FRACTURED_GEM("Fractured Gem", "A dark gem with but a single crack exposing it's beautiful, gleaming core.\nVital Rays: Significantly increases your health.", Rarity.LEGENDARY, new EventHook(
            EventHook.health(1.25f)
    )),
    NEBULA_KEY("Nebula Key", "Its owner is said to be the master of the universe. Even after his demise, his energy still resonates within.\nCosmic Field: Significantly increases your block.", Rarity.LEGENDARY, new EventHook(
            EventHook.block(1.25f)
    )),

    DARK_STEEL_ANVIL("Dark Steel Anvil", "A dwarven anvil, crafted from the fines dark steel.\nForge: Increases gear exp gained.", Rarity.UNSTABLE, new EventHook(
            Events.OBTAIN_EXP_GEAR.make(e -> e.exp.op(ex -> (int) (ex * 1.25f)))
    )),
    SPIRIT_REPLICA("Spirit Replica", "A ghostly clone of your weapon, attacking in tandem with you.\nFirst Hand: Greatly increases your damage.", Rarity.UNSTABLE, new EventHook(
            EventHook.damage(1.35f)
    )),
    CRESCENT_MASK("Crescent Mask", "A Crescent-shaped Mask, said to come from worshippers of an ancient moon god.\nLunar Blessing: Greatly increases your health.", Rarity.UNSTABLE, new EventHook(
            EventHook.health(1.35f)
    )),
    FRAGMENTED_EFFIGY("Fragmented Effigy", "A once beautiful depiction of the moon god, now blemished and broken.\nAtheist: Greatly decreases enemy block.", Rarity.UNSTABLE, new EventHook(
            Events.MONSTER_BLOCK.make(e -> e.block.op(b -> b * 0.8f))
    )),
    SASH_OF_VALOR("Sash of Valor", "A sash meant for those with great courage and incredible strength.\nMain Character: Deals additional damage based on your level.", Rarity.UNSTABLE, new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> d + (e.user.getLevel() * 10)))
    )),
    THE_FOOLS_ERRAND("The Fool's Errand", "Only a madman would accept the challenge.\nGone Mad: Halves your damage, block and health. Doubles coins and exp gained.", Rarity.UNSTABLE, new EventHook(
            EventHook.health(0.5f),
            EventHook.block(0.5f),
            EventHook.damage(0.5f),
            Events.OBTAIN_COINS.make(e -> e.coins.op(c -> c * 2)),
            Events.OBTAIN_EXP.make(e -> e.exp.op(ex -> ex * 2))
    )),
    NUCLEAR_ROCK("Nuclear Rock", "A very heavy rock that glows green at night.\nRadiation: Deals damage to you and the enemy.", Rarity.UNSTABLE, new EventHook(
            EventHook.health(0.7f),
            Events.MONSTER_HEALTH.make(e -> e.health.op(h -> (int) (h * 0.5f)))
    )),

    THE_GREAT_EQUALIZER("The great Equalizer", "This ancient magical artifact is said to bring even gods down to earth.\nOnly Human: Decreases enemy health based on its level.", Rarity.CORRUPTED, new EventHook(
            Events.MONSTER_HEALTH.make(e -> e.health.op(h -> h - (10 * e.user.getExtra().getLevel())))
    )),
    BLACK_MAGIC_WAND("Black Magic Wand", "Achieve true strength by succumbing to darkness.\nBlack Magic: Doubles your damage but halves your health.", Rarity.CORRUPTED, new EventHook(
            EventHook.damage(2f),
            EventHook.health(0.5f)
    )),
    CRYSTAL_PRIMROSE("Crystal Primrose", "A flower said to stand for eternal love, crystallized by an unknown force.\nCold Spring: Magnificently increases your health.", Rarity.CORRUPTED, new EventHook(
            EventHook.health(1.5f)
    )),
    GREATER_WILL("Greater Will", "The ethos that keeps together the world given form.\nPredetermined Outcome: Magnificently increases your block.", Rarity.CORRUPTED, new EventHook(
            EventHook.block(1.35f)
    )),
    POCKET_PORTAL("Pocket Portal", "Close any distance in an instance. Enemies will never see you coming.\nGodspeed: Magnificently increases your damage.", Rarity.CORRUPTED, new EventHook(
            EventHook.damage(1.5f)
    )),
    ETERNAL_FIRE("Eternal Fire", "A mysterious flame, that burns all you touch.\nProcessor: Greatly increases your damage, also burns all items you find.", Rarity.CORRUPTED, new EventHook(
            EventHook.damage(1.35f),
            Events.OBTAIN_ITEM.make(e -> e.item.set(Item.ASHES))
    )),

    FIRE_ORB("Fire Orb", "Light up the fire in your soul to burn away your enemies.\nA Legacy: Greatly increases your health, damage and block.", Rarity.UNIQUE, new EventHook(
            EventHook.damage(1.35f),
            EventHook.block(1.35f),
            EventHook.health(1.35f)
    ));
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Rarity rarity;
    @Getter
    private final EventHook event;

    Extra(String name, String description, Rarity rarity, EventHook event) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.event = event;
    }

    Extra(String name, String description, Rarity rarity) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.event = EventHook.NONE;
    }
}
