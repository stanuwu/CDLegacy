package com.stanuwu.cdlegacy.game.impl.title;

import com.stanuwu.cdlegacy.features.dropdown.DropdownData;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdown;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdownContext;
import com.stanuwu.cdlegacy.game.content.Title;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@DropdownData(name = "title-dropdown", maxAgeMinutes = 5, isGame = true)
public class TitleDropdown extends StringDropdown {

    @Override
    protected void doSelect(StringDropdownContext ctx) {
        ctx.disable();
        DBUser user = ctx.getUser();
        Title title = DBEnum.fromKey(ctx.getValue(), Title.class);
        if (title.getRequirement().test(user)) {
            user.setTitle(title);
            ctx.reply(
                    Embeds.success("Changed your title to " + title.getTitle() + ".").build()
            ).send();
        } else {
            ctx.reply(
                    Embeds.error(title.getRequirement().getDescription()).build()
            ).send();
        }
    }
}
