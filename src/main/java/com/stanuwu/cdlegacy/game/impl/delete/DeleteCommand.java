package com.stanuwu.cdlegacy.game.impl.delete;

import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Emojis;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

@CommandData(name = "delete", description = "Delete your character.", isGame = true)
public class DeleteCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        ctx.reply(Embeds.large("Character Deletion")
                .langDescription("delete-command").build()
        ).actionRow(
                Buttons.of("delete-button", ctx.authorId(), Emojis.WHITE_CHECK_MARK)
                        .style(ButtonStyle.SUCCESS)
                        .route("accept")
                        .build(),
                Buttons.of("delete-button", ctx.authorId(), Emojis.NEGATIVE_SQUARE_CROSS_MARK)
                        .style(ButtonStyle.DANGER)
                        .route("cancel")
                        .build()
        ).send();
    }
}
