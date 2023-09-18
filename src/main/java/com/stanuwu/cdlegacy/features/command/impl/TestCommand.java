package com.stanuwu.cdlegacy.features.command.impl;

import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.features.command.*;
import com.stanuwu.cdlegacy.features.dropdown.Dropdowns;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "test", staffCommand = true)
public class TestCommand extends BaseCommand {
    public TestCommand() {
        super(
                new CommandOptionData(OptionType.STRING, "value1", "Test Value 1", true),
                new CommandOptionData(OptionType.STRING, "value2", "Test Value 2", false,
                        new CommandOptionChoice("test", "Test Value"),
                        new CommandOptionChoice("test2", "Test Value 2")
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        StringBuilder reply = new StringBuilder();
        reply.append(ctx.getArg("value1"));
        String val2 = ctx.getArg("value2");
        if (val2 != null) {
            reply.append(" ").append(val2);
        }
        ctx
                .reply(new String(reply))
                .embeds(
                        Embeds.large("Test Embed")
                                .langDescription("test",
                                        new Placeholder("placeholder", ctx.getArg("value1"))
                                )
                                .build()
                )
                .actionRow(
                        Buttons.of("test-button", ctx.authorId(), "Send")
                                .route("send")
                                .build(),

                        Buttons.of("test-button", ctx.authorId(), "Edit")
                                .route("edit")
                                .build()
                )
                .actionRow(
                        Dropdowns.string("s-test-dropdown", ctx.authorId(), "Placeholder 1",
                                        Dropdowns.option("Option 1", "option1"),
                                        Dropdowns.option("Option 2", "option2")
                                )
                                .build()
                )
                .actionRow(
                        Dropdowns.entity("e-test-dropdown", ctx.authorId(), "Placeholder 1", Dropdowns.ROLE)
                                .build()
                )
                .send();
    }
}
