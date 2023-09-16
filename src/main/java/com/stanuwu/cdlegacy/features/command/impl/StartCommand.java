package com.stanuwu.cdlegacy.features.command.impl;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "start", description = "Create a character and start your journey.")
public class StartCommand extends BaseCommand {
    public StartCommand() {
        super(
                new CommandOptionData(OptionType.STRING, "name", "Character Name", true)
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        String name = ctx.getArg("name");
        // implement rest of commands
    }
}
