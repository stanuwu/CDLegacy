package com.stanuwu.cdlegacy.game.impl.coinflip;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

import java.util.Random;

@ButtonData(name = "coinflip-button", maxAgeMinutes = 5, isGame = true, useCache = true, complex = true)
public class CoinflipButton extends BaseButton {
    @Override
    protected void doButton(ButtonContext ctx) {
        ctx.disableAll();
        String outcome = ctx.getRoute();
        String opposite = outcome.equals("heads") ? "tails" : "heads";
        int wager = ctx.getCache().getInt("wager");
        DBUser user = ctx.getUser();
        if (user.canDoAfford(wager)) {
            boolean win = new Random().nextInt(0, 3) == 0;
            if (win) {
                user.addCoins(wager * 2L);
                ctx.reply(
                        Embeds.small("You Win")
                                .langDescription("coinflip-win",
                                        new Placeholder("side", outcome),
                                        new Placeholder("wager", "" + wager * 2L)
                                )
                                .colorSuccess()
                                .build()
                ).send();
            } else {
                ctx.reply(
                        Embeds.small("You Lose")
                                .langDescription("coinflip-lose",
                                        new Placeholder("side", opposite),
                                        new Placeholder("wager", "" + wager)
                                )
                                .colorError()
                                .build()
                ).send();
            }
        } else {
            ctx.reply(Embeds.error("You can not afford this.").build()).send();
        }
    }
}
