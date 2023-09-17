package com.stanuwu.cdlegacy.game.impl.start;

import com.stanuwu.cdlegacy.Config;
import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.game.data.DBData;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Emojis;
import com.stanuwu.cdlegacy.util.StringUtil;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

@CommandData(name = "start", description = "Create a character and start your journey.")
public class StartCommand extends BaseCommand {
    public StartCommand() {
        super(
                new CommandOptionData(OptionType.STRING, "name", "Character Name", true)
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        if (DBData.getUser(ctx.authorId()) != null) {
            ctx.reply(Embeds.error("You already have a character.").build()).send();
            return;
        }
        String name = StringUtil.truncate(StringUtil.clean(ctx.getArg("name")), 20);
        ctx.reply(Embeds.large("Character Creation")
                .langDescription("start-command",
                        new Placeholder("name", name)
                ).build()
        ).actionRow(
                Button.link(Config.TOS, "Terms of Service"),
                Button.link(Config.PP, "Privacy Policy")
        ).actionRow(
                Buttons.of("start-button", ctx.authorId(), Emojis.WHITE_CHECK_MARK)
                        .style(ButtonStyle.SUCCESS)
                        .route("accept")
                        .build(),
                Buttons.of("start-button", ctx.authorId(), Emojis.NEGATIVE_SQUARE_CROSS_MARK)
                        .style(ButtonStyle.DANGER)
                        .route("cancel")
                        .build()
        ).send().then(h ->
                ParamCache.start(
                                "start-button",
                                h.getIdLong()
                        )
                        .putString("name", name)
                        .end()
        );
    }
}
