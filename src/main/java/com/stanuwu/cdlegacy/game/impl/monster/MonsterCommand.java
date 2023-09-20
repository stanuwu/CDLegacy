package com.stanuwu.cdlegacy.game.impl.monster;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.game.content.Monster;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "monster", description = "View the stats of a monster.")
public class MonsterCommand extends BaseCommand {
    public MonsterCommand() {
        super(
                new CommandOptionData(
                        OptionType.STRING,
                        "monster",
                        "Monster Name",
                        true
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        String name = ctx.getArg("monster");
        Monster monster = DBEnum.closestMatch(name, Monster.SLIME);
        ctx.reply(
                Embeds.small("Monster")
                        .langDescription("monster-display",
                                new Placeholder("monster", monster.getName()),
                                new Placeholder("monster_level", "" + monster.getMinLevel()),
                                new Placeholder("monster_damage", "" + monster.getDamage()),
                                new Placeholder("monster_block", "%.2f".formatted((float) monster.getResistance())),
                                new Placeholder("monster_health", "" + monster.getHealth()),
                                new Placeholder("monster_desc", monster.getDescription()))
                        .build()
        ).send();
    }
}
