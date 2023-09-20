package com.stanuwu.cdlegacy.game.impl.gear;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@CommandData(name = "gear", description = "View your characters gear.", isGame = true)
public class GearCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        ctx.reply(
                Embeds.small("Stats")
                        .langDescription("gear-command",
                                new Placeholder("weapon", user.getWeapon().getType().getName()),
                                new Placeholder("weapon_rarity", user.getWeapon().getType().getRarity().getName()),
                                new Placeholder("weapon_desc", user.getWeapon().getType().getDescription()),
                                new Placeholder("weapon_lvl", user.getWeapon().formatLevel()),
                                new Placeholder("weapon_damage", "" + user.getWeapon().getDamage()),
                                new Placeholder("weapon_type", user.getWeapon().getType().formatType()),
                                new Placeholder("armor", user.getArmor().getType().getName()),
                                new Placeholder("armor_rarity", user.getArmor().getType().getRarity().getName()),
                                new Placeholder("armor_desc", user.getArmor().getType().getDescription()),
                                new Placeholder("armor_lvl", user.getArmor().formatLevel()),
                                new Placeholder("armor_block", user.getArmor().formatBlock()),
                                new Placeholder("extra", user.getExtra().getType().getName()),
                                new Placeholder("extra_rarity", user.getExtra().getType().getRarity().getName()),
                                new Placeholder("extra_desc", user.getExtra().getType().getDescription()),
                                new Placeholder("extra_lvl", user.getExtra().formatLevel())
                        )
                        .build()
        ).send();
    }
}
