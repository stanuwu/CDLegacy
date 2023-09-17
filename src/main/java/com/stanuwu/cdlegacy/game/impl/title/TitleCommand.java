package com.stanuwu.cdlegacy.game.impl.title;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.dropdown.Dropdowns;
import com.stanuwu.cdlegacy.game.content.Title;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

import java.util.ArrayList;
import java.util.List;

@CommandData(name = "title", description = "Change your title.", isGame = true)
public class TitleCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        List<Dropdowns.Option> options = new ArrayList<>();
        for (Title t : Title.values()) {
            boolean unlocked = t.getRequirement().test(user);
            Dropdowns.Option option = Dropdowns.option(t.getTitle(), DBEnum.toKey(t))
                    .desc(unlocked ? "Unlocked!" : t.getRequirement().getDescription());
            if (user.getTitle() == t) option.ticked();
            options.add(option);
        }
        ctx.reply(
                        Embeds.small("Title")
                                .description(":notepad_spiral: Pick a new title.")
                                .build()

                ).actionRow(Dropdowns.string(
                                "title-dropdown",
                                ctx.authorId(),
                                "Select your title.",
                                options.toArray(new Dropdowns.Option[0]))
                        .build()
                )
                .send();
    }
}
