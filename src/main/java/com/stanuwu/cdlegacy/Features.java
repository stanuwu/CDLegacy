package com.stanuwu.cdlegacy;

import com.stanuwu.cdlegacy.db.DB;
import com.stanuwu.cdlegacy.features.IFeature;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.game.impl.armor.ArmorCommand;
import com.stanuwu.cdlegacy.game.impl.cdclass.ClassCommand;
import com.stanuwu.cdlegacy.game.impl.cdclass.ClassDropdown;
import com.stanuwu.cdlegacy.game.impl.character.CharacterCommand;
import com.stanuwu.cdlegacy.game.impl.coinflip.CoinflipButton;
import com.stanuwu.cdlegacy.game.impl.coinflip.CoinflipCommand;
import com.stanuwu.cdlegacy.game.impl.craft.CraftButton;
import com.stanuwu.cdlegacy.game.impl.craft.CraftCommand;
import com.stanuwu.cdlegacy.game.impl.craft.CraftDropdown;
import com.stanuwu.cdlegacy.game.impl.daily.DailyCommand;
import com.stanuwu.cdlegacy.game.impl.delete.DeleteButton;
import com.stanuwu.cdlegacy.game.impl.delete.DeleteCommand;
import com.stanuwu.cdlegacy.game.impl.description.DescriptionCommand;
import com.stanuwu.cdlegacy.game.impl.door.DoorCommand;
import com.stanuwu.cdlegacy.game.impl.drop.DropButton;
import com.stanuwu.cdlegacy.game.impl.extra.ExtraCommand;
import com.stanuwu.cdlegacy.game.impl.farm.FarmButton;
import com.stanuwu.cdlegacy.game.impl.farm.FarmCommand;
import com.stanuwu.cdlegacy.game.impl.farm.FarmDropdown;
import com.stanuwu.cdlegacy.game.impl.gear.GearCommand;
import com.stanuwu.cdlegacy.game.impl.help.HelpCommand;
import com.stanuwu.cdlegacy.game.impl.inventory.InventoryCommand;
import com.stanuwu.cdlegacy.game.impl.monster.MonsterCommand;
import com.stanuwu.cdlegacy.game.impl.quest.QuestButton;
import com.stanuwu.cdlegacy.game.impl.quest.QuestCommand;
import com.stanuwu.cdlegacy.game.impl.rename.RenameCommand;
import com.stanuwu.cdlegacy.game.impl.server.ServerCommand;
import com.stanuwu.cdlegacy.game.impl.shop.ShopButton;
import com.stanuwu.cdlegacy.game.impl.shop.ShopCommand;
import com.stanuwu.cdlegacy.game.impl.shop.ShopDropdown;
import com.stanuwu.cdlegacy.game.impl.slots.SlotsCommand;
import com.stanuwu.cdlegacy.game.impl.start.StartButton;
import com.stanuwu.cdlegacy.game.impl.start.StartCommand;
import com.stanuwu.cdlegacy.game.impl.stats.StatsCommand;
import com.stanuwu.cdlegacy.game.impl.title.TitleCommand;
import com.stanuwu.cdlegacy.game.impl.title.TitleDropdown;
import com.stanuwu.cdlegacy.game.impl.top.TopCommand;
import com.stanuwu.cdlegacy.game.impl.train.TrainCommand;
import com.stanuwu.cdlegacy.game.impl.weapon.WeaponCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class Features {
    ListenerAdapter[] commands;

    public Features(DB database) {
        commands = new ListenerAdapter[]{
                // Test
                // new TestCommand(),
                // new TestDBCommand(database),
                // new TestButton(),
                // new TestStringDropdown(),
                // new TestEntityDropdown(),

                // Start
                new StartCommand(),
                new StartButton(),

                // Delete
                new DeleteCommand(),
                new DeleteButton(),

                // Character
                new CharacterCommand(),

                // Rename
                new RenameCommand(),

                // Description
                new DescriptionCommand(),

                // Stats
                new StatsCommand(),

                // Server
                new ServerCommand(),

                // Inventory
                new InventoryCommand(),

                // Coinflip
                new CoinflipCommand(),
                new CoinflipButton(),

                // Slots
                new SlotsCommand(),

                // Help
                new HelpCommand(),

                // Class
                new ClassCommand(),
                new ClassDropdown(),

                // Title
                new TitleCommand(),
                new TitleDropdown(),

                // Farm
                new FarmCommand(),
                new FarmButton(),
                new FarmDropdown(),

                // Train
                new TrainCommand(),

                // Quest
                new QuestCommand(),
                new QuestButton(),

                // Craft
                new CraftCommand(),
                new CraftButton(),
                new CraftDropdown(),

                // Drop
                new DropButton(),

                // Shop
                new ShopCommand(),
                new ShopButton(),
                new ShopDropdown(),

                // Top
                new TopCommand(),

                // Door
                new DoorCommand(),

                // Gear
                new GearCommand(),

                // Monster
                new MonsterCommand(),

                // Weapon
                new WeaponCommand(),

                // Armor
                new ArmorCommand(),

                // Extra
                new ExtraCommand(),

                // Daily
                new DailyCommand(),
        };
    }

    public void registerPre(JDABuilder b) {
        b.addEventListeners(new FeatureListener(commands));
    }

    public void registerPost(JDA jda, boolean modeDev, long devGuild) {
        if (!modeDev) {
            long id = jda.getSelfUser().getApplicationIdLong();
            for (Guild g : jda.getGuilds()) {
                g.retrieveCommands().queue(s -> {
                    for (Command d : s) {
                        if (d.getApplicationIdLong() == id) d.delete().queue();
                    }
                });
            }
        }
        for (ListenerAdapter l : commands) {
            if (l instanceof BaseCommand c) {
                if (modeDev) {
                    Guild guild = jda.getGuildById(devGuild);
                    if (guild != null) guild.upsertCommand(c.buildCommand()).queue();
                } else {
                    jda.upsertCommand(c.buildCommand()).queue();
                }
            }
        }
    }

    private static class FeatureListener extends ListenerAdapter {
        private final Map<String, ListenerAdapter> events = new HashMap<>();

        private FeatureListener(ListenerAdapter[] listeners) {
            for (ListenerAdapter l : listeners) {
                if (l instanceof IFeature f) events.put(f.getName(), l);
            }
        }

        @Override
        public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
            if (events.containsKey(event.getName())) events.get(event.getName()).onSlashCommandInteraction(event);
        }

        @Override
        public void onButtonInteraction(ButtonInteractionEvent event) {
            if (event.getButton().getId() == null) return;
            String id = idPrefix(event.getButton().getId());
            if (events.containsKey(id)) events.get(id).onButtonInteraction(event);
        }

        @Override
        public void onStringSelectInteraction(StringSelectInteractionEvent event) {
            if (event.getSelectMenu().getId() == null) return;
            String id = idPrefix(event.getSelectMenu().getId());
            if (events.containsKey(id)) events.get(id).onStringSelectInteraction(event);
        }

        @Override
        public void onEntitySelectInteraction(EntitySelectInteractionEvent event) {
            if (event.getSelectMenu().getId() == null) return;
            String id = idPrefix(event.getSelectMenu().getId());
            if (events.containsKey(id)) events.get(id).onEntitySelectInteraction(event);
        }

        private String idPrefix(String id) {
            return id.split(";")[0];
        }
    }
}
