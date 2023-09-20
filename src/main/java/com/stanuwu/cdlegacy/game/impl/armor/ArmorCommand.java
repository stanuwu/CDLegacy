package com.stanuwu.cdlegacy.game.impl.armor;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.game.content.Armor;
import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "armor", description = "View the stats of an armor.")
public class ArmorCommand extends BaseCommand {
    public ArmorCommand() {
        super(
                new CommandOptionData(
                        OptionType.STRING,
                        "armor",
                        "Armor Name",
                        true
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        String name = ctx.getArg("armor");
        Armor armor = DBEnum.closestMatch(name, Armor.RAGS);
        ctx.reply(
                Embeds.small("Weapon")
                        .langDescription("gear-display",
                                new Placeholder("item", armor.getName()),
                                new Placeholder("type", GearType.fromGear(armor).getEmoji()),
                                new Placeholder("rarity", armor.getRarity().getName()),
                                new Placeholder("stat", armor.getStat()),
                                new Placeholder("description", armor.getDescription()))
                        .build()
        ).send();
    }
}
