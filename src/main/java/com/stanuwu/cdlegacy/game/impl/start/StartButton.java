package com.stanuwu.cdlegacy.game.impl.start;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;
import com.stanuwu.cdlegacy.game.data.DBData;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@ButtonData(name = "start-button", maxAgeMinutes = 5, complex = true, useCache = true)
public class StartButton extends BaseButton {
    @Override
    protected void doButton(ButtonContext ctx) {
        ctx.disableAll();
        switch (ctx.getRoute()) {
            case "accept" -> {
                String name = ctx.getCache().getString("name");
                DBData.overrideUser(ctx.getOwnerId(), name);
                ctx.reply(Embeds.success("Character \"" + name + "\" created.").build()).send();
            }
            case "cancel" -> ctx.reply(Embeds.error("Character creation aborted.").build()).send();
        }
    }
}
