package com.stanuwu.cdlegacy.game.impl.help;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@CommandData(name = "help", description = "View information about this bots commands.")
public class HelpCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        ctx.reply(
                        Embeds.large("Help")
                                .langDescription("help-command")
                                .build()
                ).hidden()
                .send();
    }
}
