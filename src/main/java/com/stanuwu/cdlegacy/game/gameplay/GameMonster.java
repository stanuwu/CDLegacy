package com.stanuwu.cdlegacy.game.gameplay;

import com.stanuwu.cdlegacy.game.content.Monster;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.event.events.EventMonsterBlock;
import com.stanuwu.cdlegacy.game.event.events.EventMonsterDamage;
import com.stanuwu.cdlegacy.game.event.events.EventMonsterHealth;

public class GameMonster extends GameEntity {
    public GameMonster(Monster type, int level, DBUser user, DBGuild guild) {
        level -= type.getMinLevel();
        level = Math.min(0, level);
        Ref<Integer> health = Ref.of(GameScale.health(type.getHealth(), level));
        Ref<Integer> damage = Ref.of(GameScale.weaponDamage(type.getDamage(), level));
        Ref<Float> block = Ref.of(Math.min(GameScale.armorBlock(type.getResistance(), level), 75) / 100f);
        Events.MONSTER_HEALTH.invoke(new EventMonsterHealth(user, guild, health));
        Events.MONSTER_DAMAGE.invoke(new EventMonsterDamage(user, guild, damage));
        Events.MONSTER_BLOCK.invoke(new EventMonsterBlock(user, guild, block));
        this.health = health.get();
        this.damage = damage.get();
        this.block = block.get();
    }
}
