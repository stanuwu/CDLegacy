package com.stanuwu.cdlegacy.game.impl.craft;

import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.features.dropdown.DropdownData;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdown;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdownContext;
import com.stanuwu.cdlegacy.game.content.CraftingRecipe;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;

@DropdownData(name = "craft-dropdown", useCache = true, maxAgeMinutes = 5, isGame = true)
public class CraftDropdown extends StringDropdown {
    @Override
    protected void doSelect(StringDropdownContext ctx) {
        DBUser user = ctx.getUser();
        CraftingRecipe recipe = DBEnum.fromKey(ctx.getValue(), CraftingRecipe.class);
        long id = ctx.getCache().getLong("craft-id");
        ParamCache.start("craft-dropdown", id).putLong("shop-id", id).end();
        ParamCache.start("craft-button", id).putString("recipe", ctx.getValue()).end();
        ctx.reply(
                        CraftCommand.getEmbed(recipe)
                ).actionRow(CraftCommand.getDropdown(user, recipe))
                .actionRow(CraftCommand.getButton(user))
                .edit();
    }
}
