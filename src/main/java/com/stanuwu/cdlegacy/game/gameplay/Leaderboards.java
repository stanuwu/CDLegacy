package com.stanuwu.cdlegacy.game.gameplay;

import com.stanuwu.cdlegacy.Config;
import com.stanuwu.cdlegacy.game.data.DBData;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Leaderboards {
    public static Leaderboards INSTANCE;
    private final Logger logger = new SimpleLoggerFactory().getLogger("Leaderboards");
    private final JDA jda;
    private DBUser[] level = new DBUser[3];
    private String levelPic = Config.AVATAR;
    private DBUser[] coins = new DBUser[3];
    private String coinsPic = Config.AVATAR;
    private DBUser[] gear = new DBUser[3];
    private String gearPic = Config.AVATAR;
    private DBGuild[] server = new DBGuild[3];
    private final String[] serverNames = {"", "", ""};
    private String serverPic = Config.AVATAR;

    public Leaderboards(JDA jda) {
        this.jda = jda;
    }

    public MessageEmbed getLevel() {
        Embeds embed = Embeds.small("Level Top");
        if (level[0] == null)
            return embed.description("Leaderboards are updating...\nPlease wait a few seconds.").build();
        embed.thumbnail(levelPic);
        StringBuilder lead = new StringBuilder();
        lead.append("### ").append("1) ").append(level[0].titleName()).append(": ").append(level[0].formatLevel());
        if (level[1] != null)
            lead.append("\n").append("2) ").append(level[1].titleName()).append(": ").append(level[1].formatLevel());
        if (level[2] != null)
            lead.append("\n").append("3) ").append(level[2].titleName()).append(": ").append(level[2].formatLevel());
        return embed.description(new String(lead)).build();
    }

    public MessageEmbed getCoins() {
        Embeds embed = Embeds.small("Coins Top");
        if (coins[0] == null)
            return embed.description("Leaderboards are updating...\nPlease wait a few seconds.").build();
        embed.thumbnail(coinsPic);
        StringBuilder lead = new StringBuilder();
        lead.append("### ").append("1) ").append(coins[0].titleName()).append(": ").append(coins[0].formatCoins()).append(" :coin:");
        if (coins[1] != null)
            lead.append("\n").append("2) ").append(coins[1].titleName()).append(": ").append(coins[1].formatCoins()).append(" :coin:");
        if (coins[2] != null)
            lead.append("\n").append("3) ").append(coins[2].titleName()).append(": ").append(coins[2].formatCoins()).append(" :coin:");
        return embed.description(new String(lead)).build();
    }

    public MessageEmbed getGear() {
        Embeds embed = Embeds.small("Gear Top");
        if (gear[0] == null)
            return embed.description("Leaderboards are updating...\nPlease wait a few seconds.").build();
        embed.thumbnail(gearPic);
        StringBuilder lead = new StringBuilder();
        lead.append("### ").append("1) ").append(gear[0].titleName())
                .append("\n").append(":dagger: ").append(gear[0].getWeapon().getType().getName())
                .append("\n").append(":shield: ").append(gear[0].getArmor().getType().getName())
                .append("\n").append(":crystal_ball: ").append(gear[0].getExtra().getType().getName());
        if (gear[1] != null)
            lead.append("\n\n").append("2) ").append(gear[1].titleName())
                    .append("\n").append(":dagger: ").append(gear[1].getWeapon().getType().getName())
                    .append("\n").append(":shield: ").append(gear[1].getArmor().getType().getName())
                    .append("\n").append(":crystal_ball: ").append(gear[1].getExtra().getType().getName());
        if (gear[2] != null) lead.append("\n\n").append("3) ").append(gear[2].titleName())
                .append("\n").append(":dagger: ").append(gear[2].getWeapon().getType().getName())
                .append("\n").append(":shield: ").append(gear[2].getArmor().getType().getName())
                .append("\n").append(":crystal_ball: ").append(gear[2].getExtra().getType().getName());
        return embed.description(new String(lead)).build();
    }

    public MessageEmbed getServer() {
        Embeds embed = Embeds.small("Server Top");
        if (server[0] == null)
            return embed.description("Leaderboards are updating...\nPlease wait a few seconds.").build();
        embed.thumbnail(serverPic);
        StringBuilder lead = new StringBuilder();
        lead.append("### ").append("1) ").append(serverNames[0]).append(": Lvl. ").append(server[0].getLevel())
                .append("\n").append(":door: Doors: ").append(server[0].getDoorsOpened())
                .append("\n").append(":wolf: Monsters: ").append(server[0].getMonstersSlain())
                .append("\n").append(":dragon: Bosses: ").append(server[0].getBossesSlain());
        if (server[1] != null)
            lead.append("\n\n").append("2) ").append(serverNames[1]).append(": Lvl. ").append(server[1].getLevel())
                    .append("\n").append(":door: Doors: ").append(server[1].getDoorsOpened())
                    .append("\n").append(":wolf: Monsters: ").append(server[1].getMonstersSlain())
                    .append("\n").append(":dragon: Bosses: ").append(server[1].getBossesSlain());
        if (server[2] != null)
            lead.append("\n\n").append("3) ").append(serverNames[2]).append(": Lvl. ").append(server[2].getLevel())
                    .append("\n").append(":door: Doors: ").append(server[2].getDoorsOpened())
                    .append("\n").append(":wolf: Monsters: ").append(server[2].getMonstersSlain())
                    .append("\n").append(":dragon: Bosses: ").append(server[2].getBossesSlain());
        return embed.description(new String(lead)).build();
    }

    public void init() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::update, 0, 5, TimeUnit.MINUTES);
        INSTANCE = this;
    }

    public void update() {
        logger.info("STARTING LEADERBOARDS UPDATE");

        DBUser[] level = new DBUser[3];
        DBUser[] coins = new DBUser[3];
        DBUser[] gear = new DBUser[3];
        DBGuild[] server = new DBGuild[3];

        for (DBUser user : DBData.getUsers()) {
            top3(level, Leaderboards::exp, user);
            top3(coins, Leaderboards::coins, user);
            top3(gear, Leaderboards::gearValue, user);
        }

        for (DBGuild guild : DBData.getGuilds()) {
            top3(server, Leaderboards::serverLevel, guild);
        }

        this.level = level;
        if (level[0] == null) {
            levelPic = Config.AVATAR;
        } else {
            User user = this.jda.retrieveUserById(level[0].getUserId()).complete();
            if (user != null) levelPic = user.getAvatarUrl();
        }

        this.coins = coins;
        if (coins[0] == null) {
            coinsPic = Config.AVATAR;
        } else {
            User user = this.jda.retrieveUserById(coins[0].getUserId()).complete();
            if (user != null) coinsPic = user.getAvatarUrl();
        }

        this.gear = gear;
        if (gear[0] == null) {
            gearPic = Config.AVATAR;
        } else {
            User user = this.jda.retrieveUserById(gear[0].getUserId()).complete();
            if (user != null) gearPic = user.getAvatarUrl();
        }

        this.server = server;
        if (server[0] == null) {
            serverPic = Config.AVATAR;
            serverNames[0] = "" ;
        } else {
            Guild guild = this.jda.getGuildById(server[0].getGuildId());
            if (guild != null) {
                serverPic = guild.getIconUrl();
                serverNames[0] = guild.getName();
            }
        }
        if (server[1] == null) {
            serverNames[1] = "" ;
        } else {
            Guild guild = this.jda.getGuildById(server[1].getGuildId());
            if (guild != null) {
                serverNames[0] = guild.getName();
            }
        }
        if (server[2] == null) {
            serverNames[2] = "" ;
        } else {
            Guild guild = this.jda.getGuildById(server[2].getGuildId());
            if (guild != null) {
                serverNames[0] = guild.getName();
            }
        }

        logger.info("LEADERBOARD UPDATE COMPLETED");
    }

    private <T> void top3(T[] array, Function<T, Long> comparator, T member) {
        if (array[0] == null || comparator.apply(member) > comparator.apply(array[0])) {
            array[2] = array[1];
            array[1] = array[0];
            array[0] = member;
        } else if (array[1] == null || comparator.apply(member) > comparator.apply(array[1])) {
            array[2] = array[1];
            array[1] = member;
        } else if (array[2] == null || comparator.apply(member) > comparator.apply(array[2])) {
            array[2] = member;
        }
    }

    private static long exp(DBUser user) {
        return user.getExp();
    }

    private static long coins(DBUser user) {
        return user.getCoins();
    }

    private static long gearValue(DBUser user) {
        return user.getWeapon().getType().getRarity().getBuyPrice() + user.getArmor().getType().getRarity().getBuyPrice() + user.getExtra().getType().getRarity().getBuyPrice();
    }

    private static long serverLevel(DBGuild guild) {
        return guild.getLevel();
    }
}
