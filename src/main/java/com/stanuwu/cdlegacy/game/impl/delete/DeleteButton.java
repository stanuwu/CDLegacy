package com.stanuwu.cdlegacy.game.impl.delete;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@ButtonData(name = "delete-button", maxAgeMinutes = 5, complex = true, isGame = true)
public class DeleteButton extends BaseButton {
    @Override
    protected void doButton(ButtonContext ctx) {
        ctx.disableAll();
        switch (ctx.getRoute()) {
            case "accept" -> {
                ctx.getUser().delete();
                ctx.reply(Embeds.success("Character deleted.").build()).send();
            }
            case "cancel" -> ctx.reply(Embeds.error("Character deletion aborted.").build()).send();
        }
    }
}
