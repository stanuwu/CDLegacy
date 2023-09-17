package com.stanuwu.cdlegacy.game.impl.character;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.command.CommandOptionData;
import com.stanuwu.cdlegacy.game.data.DBData;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@CommandData(name = "character", description = "View your or another players character.", isGame = true)
public class CharacterCommand extends BaseCommand {
    public CharacterCommand() {
        super(
                new CommandOptionData(OptionType.USER,
                        "user",
                        "Player",
                        false
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        User u = ctx.getArgUser("user");
        DBUser user = ctx.getUser();
        if (u == null) {
            u = ctx.author();
        } else {
            user = DBData.getUser(u.getIdLong());
        }
        if (user == null) {
            ctx.reply(Embeds.error("User does not have a character.").build()).send();
            return;
        }
        ctx.reply(
                Embeds.small(user.formatName())
                        .langDescription("character-command",
                                new Placeholder("description", user.getDescription()),
                                new Placeholder("class", user.getCdClass().getName()),
                                new Placeholder("level", user.formatLevel()),
                                new Placeholder("coins", "" + user.formatCoins()),
                                new Placeholder("weapon", user.getWeapon().formatName()),
                                new Placeholder("armor", user.getArmor().formatName()),
                                new Placeholder("extra", user.getExtra().formatName())
                        ).thumbnail(u.getAvatarUrl())
                        .build()
        ).send();
    }
}
