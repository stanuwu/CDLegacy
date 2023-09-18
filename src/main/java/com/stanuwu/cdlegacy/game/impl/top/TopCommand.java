package com.stanuwu.cdlegacy.game.impl.top;

import com.stanuwu.cdlegacy.features.command.*;
import com.stanuwu.cdlegacy.game.gameplay.Leaderboards;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "top", description = "View the leaderboards.")
public class TopCommand extends BaseCommand {
    public TopCommand() {
        super(
                new CommandOptionData(
                        OptionType.STRING,
                        "type",
                        "What leaderboard to view.",
                        true,
                        new CommandOptionChoice("Level", "level"),
                        new CommandOptionChoice("Coins", "coins"),
                        new CommandOptionChoice("Gear", "gear"),
                        new CommandOptionChoice("Server", "server")
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        ctx.reply(
                switch (ctx.getArg("type")) {
                    case "level" -> Leaderboards.INSTANCE.getLevel();
                    case "coins" -> Leaderboards.INSTANCE.getCoins();
                    case "gear" -> Leaderboards.INSTANCE.getGear();
                    case "server" -> Leaderboards.INSTANCE.getServer();
                    default -> Embeds.error("").build();
                }
        ).send();
    }
}
