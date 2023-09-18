package com.stanuwu.cdlegacy.game.impl.train;

import com.stanuwu.cdlegacy.features.command.*;
import com.stanuwu.cdlegacy.game.content.Farming;
import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.content.Item;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBExtra;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.event.events.EventObtainExpGear;
import com.stanuwu.cdlegacy.game.event.events.EventObtainItem;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Timestamps;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.TimeFormat;

import java.util.Locale;
import java.util.Random;

@CommandData(name = "train", description = "Train with your gear.", isGame = true)
public class TrainCommand extends BaseCommand {
    public TrainCommand() {
        super(
                new CommandOptionData(
                        OptionType.STRING,
                        "type",
                        "Gear Type",
                        false,
                        new CommandOptionChoice(GearType.WEAPON.getName(), DBEnum.toKey(GearType.WEAPON)),
                        new CommandOptionChoice(GearType.ARMOR.getName(), DBEnum.toKey(GearType.ARMOR)),
                        new CommandOptionChoice(GearType.EXTRA.getName(), DBEnum.toKey(GearType.EXTRA))
                )
        );
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        if (user.canDoTrain(ctx.time())) {
            boolean all = ctx.getArg("type") == null;
            boolean rare = new Random().nextInt(0, 50) == 0;
            GearType type = GearType.WEAPON;
            if (!all) type = DBEnum.fromKey(ctx.getArg("type"), GearType.class);
            int reward = (all ? 5 : 15) * (rare ? 10 : 1);
            Ref<Integer> weapon = Ref.of(reward);
            Ref<Integer> armor = Ref.of(reward);
            Ref<Integer> extra = Ref.of(reward);
            StringBuilder gain = new StringBuilder();
            gain.append(rare ? "Great Success!\n" : "Success!\n");
            if(all) {
                Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, ctx.getGuild(), weapon, Ref.of(GearType.WEAPON)));
                Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, ctx.getGuild(), armor, Ref.of(GearType.ARMOR)));
                Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, ctx.getGuild(), extra, Ref.of(GearType.EXTRA)));
                gain.append("+").append(weapon.get()).append(" ").append(GearType.WEAPON.formatName()).append(" exp.\n");
                gain.append("+").append(armor.get()).append(" ").append(GearType.ARMOR.formatName()).append(" exp.\n");
                gain.append("+").append(extra.get()).append(" ").append(GearType.EXTRA.formatName()).append(" exp.");
            } else {
                Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, ctx.getGuild(), weapon, Ref.of(type)));
                gain.append("+").append(weapon.get()).append(" ").append(type.formatName()).append(" exp.");
            }
            ctx.reply(Embeds.small("Train").description(new String(gain)).build()).send();
        } else {
            ctx.reply(Embeds.error("You can train again " + Timestamps.toTimestamp(TimeFormat.RELATIVE, user.canTrainAt()) + ".").build()).send();
        }
    }
}
