package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

public enum DamageType {
    SWORD("Sword", DamageArchtype.PHYSICAL_CLOSE),
    BOW("Bow", DamageArchtype.PHYSICAL_FAR),
    DAGGER("Dagger", DamageArchtype.PHYSICAL_CLOSE),
    AXE("Axe", DamageArchtype.PHYSICAL_CLOSE),
    BLUNT("Blunt", DamageArchtype.PHYSICAL_CLOSE),
    WAND("Wand", DamageArchtype.MAGIC),
    LANCE("Lance", DamageArchtype.PHYSICAL_FAR),
    BOOMERANG("Boomerang", DamageArchtype.PHYSICAL_FAR),
    SPIRIT("Spirit", DamageArchtype.MAGIC),
    SIGIL("Sigil", DamageArchtype.MAGIC),
    FIREARM("Firearm", DamageArchtype.PHYSICAL_FAR),
    SICKLE("Sickle", DamageArchtype.PHYSICAL_CLOSE),
    SCYTHE("Scythe", DamageArchtype.PHYSICAL_CLOSE),
    ;
    @Getter
    private final String name;
    @Getter
    private final DamageArchtype archtype;

    DamageType(String name, DamageArchtype archtype) {
        this.name = name;
        this.archtype = archtype;
    }
}
