package com.stanuwu.cdlegacy.game.impl.cdclass;

import com.stanuwu.cdlegacy.features.dropdown.DropdownData;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdown;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdownContext;
import com.stanuwu.cdlegacy.game.content.CDClass;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@DropdownData(name = "class-dropdown", maxAgeMinutes = 5, isGame = true)
public class ClassDropdown extends StringDropdown {
    @Override
    protected void doSelect(StringDropdownContext ctx) {
        ctx.disable();
        DBUser user = ctx.getUser();
        CDClass cdClass = DBEnum.fromKey(ctx.getValue(), CDClass.class);
        if (cdClass.getRequirement().test(user)) {
            user.setCdClass(cdClass);
            ctx.reply(
                    Embeds.success("Changed your class to " + cdClass.getName() + ".").build()
            ).send();
        } else {
            ctx.reply(
                    Embeds.error(cdClass.getRequirement().getDescription()).build()
            ).send();
        }
    }
}
