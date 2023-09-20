package com.stanuwu.cdlegacy.game.impl.daily;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.event.events.EventObtainCoins;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Timestamps;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.TimeFormat;

@CommandData(name = "daily", description = "Vote for the bot and earn rewards.", isGame = true)
public class DailyCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        if (user.canDoVote(ctx.time())) {
            Ref<Integer> coins = Ref.of(user.getLevel() * 50);
            Events.OBTAIN_COINS.invoke(new EventObtainCoins(user, ctx.getGuild(), coins));
            ctx.reply(Embeds.small("Daily")
                    .description(":coin: +" + coins.get() + " coins")
                    .build()
            ).send();
        } else if (user.canVote(ctx.time())) {
            ctx.reply(Embeds.small("Vote")
                            .langDescription("vote")
                            .build()
                    ).actionRow(
                            Button.link("https://top.gg/bot/717757487482273813", "Vote"),
                            Button.link("https://top.gg/bot/717757487482273813#reviews", "Review")
                    )
                    .send();
        } else {
            ctx.reply(Embeds.error("You can claim another daily " + Timestamps.toTimestamp(TimeFormat.RELATIVE, user.canVoteAt()) + ".").build()).send();
        }
    }
}
