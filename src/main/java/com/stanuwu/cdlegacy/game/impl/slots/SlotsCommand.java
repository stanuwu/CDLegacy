package com.stanuwu.cdlegacy.game.impl.slots;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.Random;

@CommandData(name = "slots", description = "Play a slot machine and wager your coins.", isGame = true)
public class SlotsCommand extends BaseCommand {
    private static final String[] results = {":knife:", ":grapes:", ":cherries:", ":seven:", ":bell:", ":heart:"};

    public SlotsCommand() {
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
        DBUser user = ctx.getUser();
        if (wager < 1) {
            ctx.reply(Embeds.error("Invalid wager.").build()).send();
            return;
        }
        if (user.canDoAfford(wager)) {
            Random random = new Random();
            String pick1 = results[random.nextInt(0, results.length)];
            String pick2 = results[random.nextInt(0, results.length)];
            String pick3 = results[random.nextInt(0, results.length)];
            boolean win = pick1.equals(pick2) && pick2.equals(pick3);
            if (win) user.addCoins(wager * 10L);
            ctx.reply(
                    Embeds.small("Slots")
                            .langDescription("slots-command",
                                    new Placeholder("pick1", pick1),
                                    new Placeholder("pick2", pick2),
                                    new Placeholder("pick3", pick3),
                                    new Placeholder("status", win ? "win " + wager * 10L : "lose " + wager)
                            )
                            .build()
            ).send();
        } else {
            ctx.reply(Embeds.error("You can not afford this.").build()).send();
        }
    }
}
