package com.stanuwu.cdlegacy.game.gameplay;

import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.event.events.EventPlayerBlock;
import com.stanuwu.cdlegacy.game.event.events.EventPlayerDamage;
import com.stanuwu.cdlegacy.game.event.events.EventPlayerHealth;

public class GamePlayer extends GameEntity {
    public GamePlayer(int roll, DBUser user, DBGuild guild, GameMonster monster) {
        int level = Math.min(0, user.getLevel() - roll + 5);
        Ref<Integer> health = Ref.of(GameScale.health(100, level));
        Ref<Integer> damage = Ref.of(GameScale.weaponDamage(user.getWeapon().getType().getDamage(), level));
        Ref<Float> block = Ref.of(Math.min(GameScale.armorBlock(user.getArmor().getType().getReduction(), level), 50) / 100f);
        Events.PLAYER_HEALTH.invoke(new EventPlayerHealth(user, guild, monster, health));
        Events.PLAYER_DAMAGE.invoke(new EventPlayerDamage(user, guild, monster, damage));
        Events.PLAYER_BLOCK.invoke(new EventPlayerBlock(user, guild, monster, block));
        this.health = health.get();
        this.damage = damage.get();
        this.block = block.get();
    }
}
