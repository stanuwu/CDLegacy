package com.stanuwu.cdlegacy.game.impl.rename;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "rename", description = "Change your character name.", isGame = true)
public class RenameCommand extends BaseCommand {
    public RenameCommand() {
        super(
                new CommandOptionData(
                        OptionType.STRING,
                        "name",
                        "New Namen",
                        true
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        ctx.getUser().setName(ctx.getArg("name"));
        ctx.reply(Embeds.success("Updated your name.").build()).send();
    }
}
