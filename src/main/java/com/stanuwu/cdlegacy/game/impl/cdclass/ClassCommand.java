package com.stanuwu.cdlegacy.game.impl.cdclass;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.dropdown.Dropdowns;
import com.stanuwu.cdlegacy.game.content.CDClass;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

import java.util.ArrayList;
import java.util.List;

@CommandData(name = "class", description = "Change your characters class.", isGame = true)
public class ClassCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        List<Dropdowns.Option> options = new ArrayList<>();
        for (CDClass c : CDClass.values()) {
            Dropdowns.Option option = Dropdowns.option(c.getName(), DBEnum.toKey(c))
                    .desc(c.getDescription());
            if (user.getCdClass() == c) option.ticked();
            options.add(option);
        }
        ctx.reply(
                        Embeds.small("Class")
                                .description(":bust_in_silhouette: Pick a new class.")
                                .build()

                ).actionRow(Dropdowns.string(
                                "class-dropdown",
                                ctx.authorId(),
                                "Select your class.",
                                options.toArray(new Dropdowns.Option[0]))
                        .build()
                )
                .send();
    }
}
