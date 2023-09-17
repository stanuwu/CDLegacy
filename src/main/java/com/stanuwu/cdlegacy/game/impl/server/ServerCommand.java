package com.stanuwu.cdlegacy.game.impl.server;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.entities.Guild;

@CommandData(name = "server", description = "View stats about the current server.", isGame = true)
public class ServerCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        Guild g = ctx.guild();
        DBGuild guild = ctx.getGuild();
        ctx.reply(
                Embeds.small(guild.formatName(g.getName()))
                        .langDescription("server-command",
                                new Placeholder("doors", "" + guild.getDoorsOpened()),
                                new Placeholder("monsters", "" + guild.getMonstersSlain()),
                                new Placeholder("bosses", "" + guild.getBossesSlain()),
                                new Placeholder("bonus", guild.formatBonus())
                        )
                        .thumbnail(g.getIconUrl())
                        .build()
        ).send();
    }
}
