package com.stanuwu.cdlegacy.game.impl.description;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "description", description = "Change your description.", isGame = true)
public class DescriptionCommand extends BaseCommand {
    public DescriptionCommand() {
        super(
                new CommandOptionData(
                        OptionType.STRING,
                        "description",
                        "New Description",
                        true
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        ctx.getUser().setDescription(ctx.getArg("description"));
        ctx.reply(Embeds.success("Updated your description.").build()).send();
    }
}
