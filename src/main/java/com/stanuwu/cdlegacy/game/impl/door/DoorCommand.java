package com.stanuwu.cdlegacy.game.impl.door;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.event.events.EventFindChest;
import com.stanuwu.cdlegacy.game.event.events.EventObtainCoins;
import com.stanuwu.cdlegacy.game.event.events.EventOpenDoor;
import com.stanuwu.cdlegacy.game.gameplay.Game;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Timestamps;
import net.dv8tion.jda.api.utils.TimeFormat;

import java.util.Random;

@CommandData(name = "door", description = "Open a new dungeon room.", isGame = true)
public class DoorCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        if (!user.canDoDoor(ctx.time())) {
            ctx.reply(Embeds.error("You open another door " + Timestamps.toTimestamp(TimeFormat.RELATIVE, user.canDoorAt()) + ".").build()).send();
            return;
        }
        DBGuild guild = ctx.getGuild();
        Events.OPEN_DOOR.invoke(new EventOpenDoor(user, guild));
        Random random = new Random();
        boolean lucky = random.nextInt(0, 11) == 0;
        if (lucky) {
            Events.FIND_CHEST.invoke(new EventFindChest(user, guild));
            Ref<Integer> coins = Ref.of(random.nextInt(100, 250 + user.getLevel() * 10));
            Events.OBTAIN_COINS.invoke(new EventObtainCoins(user, guild, coins));
            boolean veryLucky = random.nextInt(0, 21) == 0;
            ctx.reply(
                    Embeds.small("Treasure Chest")
                            .langDescription("door-chest",
                                    new Placeholder("coins", "" + coins.get()),
                                    new Placeholder("bonus", veryLucky ? "\n:star2: Rare Drop! :star2:" : "")
                            )
                            .build()
            ).send().then(
                    m -> {
                        if (veryLucky) {
                            Game.dropRandomGear(user, guild, ctx.message(), ctx.time());
                        }
                    }
            );
        } else {
            Game.FightResult res = Game.simulateFight(user, ctx.getGuild());
            ctx.reply(res.res()).send()
                    .then(m -> {
                        if (random.nextInt(0, 101) == 0 && res.win())
                            Game.dropRandomGear(user, guild, ctx.message(), ctx.time());
                    });
        }
    }
}
