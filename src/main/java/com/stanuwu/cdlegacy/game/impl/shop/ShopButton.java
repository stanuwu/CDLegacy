package com.stanuwu.cdlegacy.game.impl.shop;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;
import com.stanuwu.cdlegacy.game.content.ShopEntry;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.gameplay.Game;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@ButtonData(name = "shop-button", maxAgeMinutes = 5, isGame = true, useCache = true)
public class ShopButton extends BaseButton {
    @Override
    protected void doButton(ButtonContext ctx) {
        ctx.disableAll();
        DBUser user = ctx.getUser();
        ShopEntry entry = DBEnum.fromKey(ctx.getCache().getString("entry"), ShopEntry.class);
        if (user.canDoAfford(entry.getPrice())) {
            Game.dropGear(user, ctx.getGuild(), ctx.reply(), entry.procureItem(), ctx.time());
        } else {
            ctx.reply(Embeds.error("You can not afford this.").build()).send();
        }
    }
}
