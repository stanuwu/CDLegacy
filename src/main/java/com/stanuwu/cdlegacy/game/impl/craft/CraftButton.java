package com.stanuwu.cdlegacy.game.impl.craft;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;
import com.stanuwu.cdlegacy.game.content.CraftingRecipe;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.gameplay.Game;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@ButtonData(name = "craft-button", maxAgeMinutes = 5, isGame = true, useCache = true)
public class CraftButton extends BaseButton {
    @Override
    protected void doButton(ButtonContext ctx) {
        ctx.disableAll();
        DBUser user = ctx.getUser();
        CraftingRecipe recipe = DBEnum.fromKey(ctx.getCache().getString("recipe"), CraftingRecipe.class);
        if (user.getInv().hasDoRemoveAll(recipe.getCost())) {
            Game.dropGear(user, ctx.reply(), recipe.getGear(), ctx.time());
        } else {
            ctx.reply(Embeds.error("You can not afford this.").build()).send();
        }
    }
}
