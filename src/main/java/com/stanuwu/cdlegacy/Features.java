package com.stanuwu.cdlegacy;

import com.stanuwu.cdlegacy.db.DB;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.game.impl.cdclass.ClassCommand;
import com.stanuwu.cdlegacy.game.impl.cdclass.ClassDropdown;
import com.stanuwu.cdlegacy.game.impl.character.CharacterCommand;
import com.stanuwu.cdlegacy.game.impl.coinflip.CoinflipButton;
import com.stanuwu.cdlegacy.game.impl.coinflip.CoinflipCommand;
import com.stanuwu.cdlegacy.game.impl.craft.CraftButton;
import com.stanuwu.cdlegacy.game.impl.craft.CraftCommand;
import com.stanuwu.cdlegacy.game.impl.craft.CraftDropdown;
import com.stanuwu.cdlegacy.game.impl.delete.DeleteButton;
import com.stanuwu.cdlegacy.game.impl.delete.DeleteCommand;
import com.stanuwu.cdlegacy.game.impl.description.DescriptionCommand;
import com.stanuwu.cdlegacy.game.impl.drop.DropButton;
import com.stanuwu.cdlegacy.game.impl.farm.FarmButton;
import com.stanuwu.cdlegacy.game.impl.farm.FarmCommand;
import com.stanuwu.cdlegacy.game.impl.farm.FarmDropdown;
import com.stanuwu.cdlegacy.game.impl.help.HelpCommand;
import com.stanuwu.cdlegacy.game.impl.inventory.InventoryCommand;
import com.stanuwu.cdlegacy.game.impl.quest.QuestButton;
import com.stanuwu.cdlegacy.game.impl.quest.QuestCommand;
import com.stanuwu.cdlegacy.game.impl.rename.RenameCommand;
import com.stanuwu.cdlegacy.game.impl.server.ServerCommand;
import com.stanuwu.cdlegacy.game.impl.slots.SlotsCommand;
import com.stanuwu.cdlegacy.game.impl.start.StartButton;
import com.stanuwu.cdlegacy.game.impl.start.StartCommand;
import com.stanuwu.cdlegacy.game.impl.stats.StatsCommand;
import com.stanuwu.cdlegacy.game.impl.title.TitleCommand;
import com.stanuwu.cdlegacy.game.impl.title.TitleDropdown;
import com.stanuwu.cdlegacy.game.impl.train.TrainCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

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
        };
    }

    public void registerPre(JDABuilder b) {
        b.addEventListeners((Object[]) commands);
    }

    public void registerPost(JDA jda, boolean modeDev, long devGuild) {
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
}
