package com.stanuwu.cdlegacy.game.impl.coinflip;

import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

@CommandData(name = "coinflip", description = "Flip a coin and bet on the outcome.", isGame = true)
public class CoinflipCommand extends BaseCommand {
    public CoinflipCommand() {
        super(
                new CommandOptionData(
                        OptionType.INTEGER,
                        "wager",
                        "Coin Wager",
                        true
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        int wager = ctx.getArgInt("wager");
        if (wager < 1) {
            ctx.reply(Embeds.error("Invalid wager.").build()).send();
            return;
        }
        ctx.reply(
                        Embeds.small("Coinflip")
                                .langDescription(
                                        "coinflip-command",
                                        new Placeholder("wager", "" + wager)
                                ).build()
                ).actionRow(
                        Buttons.of("coinflip-button",
                                        ctx.authorId(),
                                        "Heads"
                                )
                                .route("heads")
                                .style(ButtonStyle.PRIMARY)
                                .build(),
                        Buttons.of("coinflip-button",
                                        ctx.authorId(),
                                        "Tails"
                                )
                                .route("tails")
                                .style(ButtonStyle.SECONDARY)
                                .build()
                )
                .send().then(m -> ParamCache.start(
                                "coinflip-button",
                                m.getIdLong()
                        )
                        .putInt("wager", wager)
                        .end());
    }
}
