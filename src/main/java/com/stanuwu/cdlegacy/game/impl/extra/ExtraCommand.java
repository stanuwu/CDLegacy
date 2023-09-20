package com.stanuwu.cdlegacy.game.impl.extra;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.game.content.Extra;
import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "extra", description = "View the stats of an extra.")
public class ExtraCommand extends BaseCommand {
    public ExtraCommand() {
        super(
                new CommandOptionData(
                        OptionType.STRING,
                        "extra",
                        "Extra Name",
                        true
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        String name = ctx.getArg("extra");
        Extra extra = DBEnum.closestMatch(name, Extra.PENDANT);
        ctx.reply(
                Embeds.small("Weapon")
                        .langDescription("gear-display",
                                new Placeholder("item", extra.getName()),
                                new Placeholder("type", GearType.fromGear(extra).getEmoji()),
                                new Placeholder("rarity", extra.getRarity().getName()),
                                new Placeholder("stat", extra.getStat()),
                                new Placeholder("description", extra.getDescription()))
                        .build()
        ).send();
    }
}

