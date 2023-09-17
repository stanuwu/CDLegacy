package com.stanuwu.cdlegacy.game.impl.farm;

import com.stanuwu.cdlegacy.features.dropdown.DropdownData;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdown;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdownContext;
import com.stanuwu.cdlegacy.game.content.Farming;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;

@DropdownData(name = "farm-dropdown", maxAgeMinutes = 5, isGame = true)
public class FarmDropdown extends StringDropdown {
    @Override
    protected void doSelect(StringDropdownContext ctx) {
        Farming farming = DBEnum.fromKey(ctx.getValue(), Farming.class);
        DBUser user = ctx.getUser();

        user.setFarming(farming);
        ctx.reply(
                        FarmCommand.getEmbed(user)
                ).actionRow(
                        FarmCommand.getDropdowns(user)
                ).actionRow(
                        FarmCommand.getButton(user)
                )
                .edit();
    }
}
