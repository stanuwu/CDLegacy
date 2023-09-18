package com.stanuwu.cdlegacy.game.impl.shop;

import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.features.dropdown.DropdownData;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdown;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdownContext;
import com.stanuwu.cdlegacy.game.content.ShopEntry;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;

@DropdownData(name = "shop-dropdown", useCache = true, maxAgeMinutes = 5, isGame = true)
public class ShopDropdown extends StringDropdown {
    @Override
    protected void doSelect(StringDropdownContext ctx) {
        DBUser user = ctx.getUser();
        ShopEntry recipe = DBEnum.fromKey(ctx.getValue(), ShopEntry.class);
        long id = ctx.getCache().getLong("shop-id");
        ParamCache.start("shop-dropdown", id).putLong("shop-id", id).end();
        ParamCache.start("shop-button", id).putString("entry", ctx.getValue()).end();
        ctx.reply(
                        ShopCommand.getEmbed(recipe)
                ).actionRow(ShopCommand.getDropdown(user, recipe))
                .actionRow(ShopCommand.getButton(user))
                .edit();
    }
}
