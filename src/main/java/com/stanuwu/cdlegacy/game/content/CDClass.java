package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;
import com.stanuwu.cdlegacy.game.event.Events;
import lombok.Getter;

public enum CDClass {
    ADVENTURER("Adventurer", "Receive a 5% EXP bonus.", new EventHook(
            Events.OBTAIN_EXP.make(e -> e.exp.op(ex -> (int) (ex * 1.05f)))
    )),
    SAGE("Sage", "Receive a 7% EXP bonus.", new EventHook(
            Events.OBTAIN_EXP.make(e -> e.exp.op(ex -> (int) (ex * 1.07f)))
    ), Requirement.LEGACY),
    THIEF("Thief", "Receive a 5% coin bonus.", new EventHook(
            Events.OBTAIN_COINS.make(e -> e.coins.op(c -> (int) (c * 1.05f)))
    )),
    BLACKSMITH("Blacksmith", "Receive a 5% gear EXP bonus.", new EventHook(
            Events.OBTAIN_EXP_GEAR.make(e -> e.exp.op(ex -> (int) (ex * 1.05f)))
    )),
    BRAWLER("Brawler", "Receive a 5% close range damage bonus.", new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType().getArchtype() == DamageArchtype.PHYSICAL_CLOSE ? (int) (d * 1.05f) : d))
    ), Requirement.level(5)),
    RANGER("Ranger", "Receive a 5% far range damage bonus.", new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType().getArchtype() == DamageArchtype.PHYSICAL_FAR ? (int) (d * 1.05f) : d))
    ), Requirement.level(5)),
    MAGE("Mage", "Receive a 5% magic damage bonus.", new EventHook(
            Events.PLAYER_DAMAGE.make(e -> e.damage.op(d -> e.user.getWeapon().getType().getDamageType().getArchtype() == DamageArchtype.MAGIC ? (int) (d * 1.05f) : d))
    ), Requirement.level(5)),
    WARRIOR("Warrior", "Get a 10% bonus to all damage.", new EventHook(
            EventHook.damage(1.1f)
    ), Requirement.slain(100)),
    TANK("Tank", "Get a 5% bonus to all block.", new EventHook(
            EventHook.block(1.05f)
    ), Requirement.opened(200)),
    DRUID("Druid", "Get a 10% bonus to health.", new EventHook(
            EventHook.health(1.1f)
    ), Requirement.found(10)),
    FARMER("Farmer", "Get an additional item while farming.", new EventHook(
            Events.OBTAIN_ITEM.make(e -> e.count.op(c -> ++c))
    ), Requirement.level(10)),
    DEITY("Deity", "Get a 5% bonus to damage, block and health.", new EventHook(
            EventHook.damage(1.05f),
            EventHook.block(1.05f),
            EventHook.health(1.05f)
    ), Requirement.level(30)),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final EventHook event;
    @Getter
    private final Requirement requirement;

    CDClass(String name, String description, EventHook event, Requirement requirement) {
        this.name = name;
        this.description = description;
        this.requirement = requirement;
        this.event = event;
    }

    CDClass(String name, String description, EventHook event) {
        this.name = name;
        this.description = description;
        this.event = event;
        this.requirement = Requirement.TRUE;
    }
}
