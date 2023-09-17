package com.stanuwu.cdlegacy.game.impl.farm;

import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.dropdown.Dropdowns;
import com.stanuwu.cdlegacy.game.content.Farming;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.util.ArrayList;
import java.util.List;

@CommandData(name = "farm", description = "Farm some items.", isGame = true)
public class FarmCommand extends BaseCommand {
    static MessageEmbed getEmbed(DBUser user) {
        return Embeds.small("Farm")
                .description(user.getFarming().getEmoji() + " " + user.getFarming().getName())
                .build();
    }

    static StringSelectMenu getDropdowns(DBUser user) {
        List<Dropdowns.Option> options = new ArrayList<>();
        for (Farming f : Farming.values()) {
            Dropdowns.Option option = Dropdowns.option(f.getName(), DBEnum.toKey(f))
                    .desc("Common Drop: " + f.getCommon().getName() + " | Rare Drop: " + f.getRare().getName());
            if (user.getFarming() == f) option.ticked();
            options.add(option);
        }
        return Dropdowns.string(
                        "farm-dropdown",
                        user.getUserId(),
                        "Select what to farm.",
                        options.toArray(new Dropdowns.Option[0]))
                .build();
    }

    static Button getButton(DBUser user) {
        return Buttons.of(
                "farm-button",
                user.getUserId(),
                "Farm"
        ).build();
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        ctx.reply(
                        getEmbed(user)
                ).actionRow(
                        getDropdowns(user)
                ).actionRow(
                        getButton(user)
                )
                .send();
    }
}
