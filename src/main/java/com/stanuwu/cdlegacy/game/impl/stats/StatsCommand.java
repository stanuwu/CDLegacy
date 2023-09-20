package com.stanuwu.cdlegacy.game.impl.stats;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@CommandData(name = "stats", description = "View your game stats.", isGame = true)
public class StatsCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        ctx.reply(
                Embeds.small("Stats")
                        .langDescription("stats-command",
                                new Placeholder("monsters", "" + user.getMonstersSlain()),
                                new Placeholder("doors", "" + user.getDoorsOpened()),
                                new Placeholder("bosses", "" + user.getBossesSlain()),
                                new Placeholder("items", "" + user.getItemsFound()),
                                new Placeholder("chests", "" + user.getChestsOpened())
                        )
                        .build()
        ).send();
    }
}
