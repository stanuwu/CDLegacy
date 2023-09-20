package com.stanuwu.cdlegacy.game.gameplay;

import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.content.IGear;
import com.stanuwu.cdlegacy.game.content.Monster;
import com.stanuwu.cdlegacy.game.content.Rarity;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.event.events.*;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.ReplyContext;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Emojis;
import com.stanuwu.cdlegacy.util.Timestamps;
import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.utils.TimeFormat;

import java.time.LocalDateTime;
import java.util.Random;

@UtilityClass
public class Game {
    public FightResult simulateFight(DBUser user, DBGuild guild) {
        Random random = new Random();
        int monsterLevel = Math.max(user.getLevel() - 3 + random.nextInt(0, 7), 0);
        Monster type = Monster.randomForLevel(user.getLevel());
        GameMonster monster = new GameMonster(type, monsterLevel, user, guild);
        int playerRoll = random.nextInt(0, 11);
        GamePlayer player = new GamePlayer(playerRoll, user, guild, monster);
        boolean headstart = playerRoll > 4;
        Simulation simulation = new Simulation(headstart ? player : monster, headstart ? monster : player);
        boolean winner = simulation.fight();
        if (!headstart) winner = !winner;
        int playerHealth = headstart ? simulation.getAttackerHealth() : simulation.getDefenderHealth();
        int monsterHealth = headstart ? simulation.getDefenderHealth() : simulation.getAttackerHealth();
        Embeds embed = Embeds.small("A Monster Appears").langDescription("fight-desc",
                new Placeholder("monster", type.getName()),
                new Placeholder("monster_level", "" + monsterLevel),
                new Placeholder("monster_damage", "" + monster.getDamage()),
                new Placeholder("monster_block", monster.formatBlock()),
                new Placeholder("monster_desc", type.getDescription()),
                new Placeholder("roll", "" + playerRoll),
                new Placeholder("status", winner ? "You Win!" : "You Lose!"
                ),
                new Placeholder("success", playerRoll == 0 ? " Critical Fail!" : playerRoll == 10 ? "Critical Success!" : ""));
        embed.field("Your HP", playerHealth + "/" + player.getHealth());
        embed.field("Enemy HP", monsterHealth + "/" + monster.getHealth());
        if (winner) {
            embed.colorSuccess();
            Ref<Integer> coins = Ref.of(reward(user));
            Ref<Integer> exp = Ref.of(reward(user) / 2);
            Ref<Integer> weapon = Ref.of(reward(user) / 4);
            Ref<Integer> armor = Ref.of(reward(user) / 4);
            Ref<Integer> extra = Ref.of(reward(user) / 4);
            Events.OBTAIN_COINS.invoke(new EventObtainCoins(user, guild, coins));
            Events.OBTAIN_EXP.invoke(new EventObtainExp(user, guild, exp));
            Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, guild, weapon, Ref.of(GearType.WEAPON)));
            Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, guild, armor, Ref.of(GearType.ARMOR)));
            Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, guild, extra, Ref.of(GearType.EXTRA)));
            StringBuilder rewards = new StringBuilder();
            rewards.append(":coin: +").append(coins.get()).append(" coins").append("\n");
            rewards.append(":diamond_shape_with_a_dot_inside: +").append(exp.get()).append(" EXP").append("\n");
            rewards.append("+").append(weapon.get()).append(" :dagger: Weapon EXP").append("\n");
            rewards.append("+").append(armor.get()).append(" :shield: Armor EXP").append("\n");
            rewards.append("+").append(extra.get()).append(" :crystal_ball: Extra EXP");
            embed.field("Rewards", new String(rewards));
            Events.SLAY_MONSTER.invoke(new EventSlayMonster(user, guild));
        } else embed.colorError();
        return new FightResult(winner, embed.build());
    }

    public void dropRandomGear(DBUser user, DBGuild guild, ReplyContext reply, LocalDateTime time) {
        Rarity rarity = Rarity.getRandomRarity();
        dropGear(user, guild, reply, rarity.getRandomItemFor(), time);
    }

    public void dropGear(DBUser user, DBGuild guild, ReplyContext reply, IGear gear, LocalDateTime time) {
        GearType type = GearType.fromGear(gear);
        Events.GEAR_DROP.invoke(new EventGearDrop(user, guild, gear));
        reply.embeds(
                        Embeds.small(gear.getRarity().getName() + " Drop")
                                .langDescription("drop-desc",
                                        new Placeholder("type", type.getEmoji()),
                                        new Placeholder("item", gear.getName()),
                                        new Placeholder("rarity", gear.getRarity().getName()),
                                        new Placeholder("description", gear.getDescription()),
                                        new Placeholder("price", "" + gear.getRarity().getSellPrice()),
                                        new Placeholder("infuse", "" + gear.getRarity().getInfuseValue()),
                                        new Placeholder("ltype", type.getName().toLowerCase()),
                                        new Placeholder("timestamp", Timestamps.toTimestamp(TimeFormat.RELATIVE, time.plusMinutes(15))),
                                        new Placeholder("stat", gear.getStat())
                                )
                                .build()
                )
                .actionRow(
                        Buttons.of(
                                "drop-button",
                                user.getUserId(),
                                "Claim",
                                Emojis.WHITE_CHECK_MARK
                        ).route("claim").build(),
                        Buttons.of(
                                "drop-button",
                                user.getUserId(),
                                "Sell",
                                Emojis.COIN
                        ).route("sell").build(),
                        Buttons.of(
                                "drop-button",
                                user.getUserId(),
                                "Infuse",
                                Emojis.DIAMOND_SHAPE_WITH_A_DOT_INSIDE
                        ).route("infuse").build()
                )
                .send().then(m -> ParamCache.start("drop-button", m.getIdLong())
                        .putString("type", DBEnum.toKey(type))
                        .putString("gear", gear.name())
                        .putString("rarity", DBEnum.toKey(gear.getRarity()))
                        .end());
    }

    private int reward(DBUser user) {
        return new Random().nextInt(50, 150 + user.getLevel() * 10);
    }

    public record FightResult(boolean win, MessageEmbed res) {
    }
}
