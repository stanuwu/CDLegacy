package com.stanuwu.cdlegacy.game.impl.farm;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;
import com.stanuwu.cdlegacy.game.content.Farming;
import com.stanuwu.cdlegacy.game.content.Item;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Timestamps;
import net.dv8tion.jda.api.utils.TimeFormat;

import java.util.Random;

@ButtonData(name = "farm-button", maxAgeMinutes = 5, isGame = true)
public class FarmButton extends BaseButton {
    @Override
    protected void doButton(ButtonContext ctx) {
        ctx.disableAll();
        DBUser user = ctx.getUser();
        if (user.canDoFarm(ctx.time())) {
            Farming farming = user.getFarming();
            Random random = new Random();
            boolean rare = random.nextInt(0, 50) == 0;
            Item gain = rare ? farming.getRare() : farming.getCommon();
            int count = rare ? 1 : random.nextInt(1, 4);
            user.getInv().add(gain, count);
            ctx.reply(Embeds.success("You obtained " + count + "x " + gain.getName() + ".").build()).send();
        } else {
            ctx.reply(Embeds.error("You can farm again " + Timestamps.toTimestamp(TimeFormat.RELATIVE, user.canFarmAt()) + ".").build()).send();
        }
    }
}
