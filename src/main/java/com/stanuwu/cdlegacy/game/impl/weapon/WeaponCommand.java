package com.stanuwu.cdlegacy.game.impl.weapon;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.content.Weapon;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "weapon", description = "View the stats of a weapon.")
public class WeaponCommand extends BaseCommand {
    public WeaponCommand() {
        super(
                new CommandOptionData(
                        OptionType.STRING,
                        "weapon",
                        "Weapon Name",
                        true
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        String name = ctx.getArg("weapon");
        Weapon weapon = DBEnum.closestMatch(name, Weapon.STICK);
        ctx.reply(
                Embeds.small("Weapon")
                        .langDescription("gear-display",
                                new Placeholder("item", weapon.getName()),
                                new Placeholder("type", GearType.fromGear(weapon).getEmoji()),
                                new Placeholder("rarity", weapon.getRarity().getName()),
                                new Placeholder("stat", weapon.getStat()),
                                new Placeholder("description", weapon.getDescription()))
                        .build()
        ).send();
    }
}
