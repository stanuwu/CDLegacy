package com.stanuwu.cdlegacy.game.impl.train;

import com.stanuwu.cdlegacy.features.command.*;
import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.event.events.EventObtainExpGear;
import com.stanuwu.cdlegacy.game.event.events.EventTrain;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Timestamps;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.TimeFormat;

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
            DBGuild guild = ctx.getGuild();
            if (all) {
                Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, guild, weapon, Ref.of(GearType.WEAPON)));
                Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, guild, armor, Ref.of(GearType.ARMOR)));
                Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, guild, extra, Ref.of(GearType.EXTRA)));
                gain.append("+").append(weapon.get()).append(" ").append(GearType.WEAPON.formatName()).append(" EXP\n");
                gain.append("+").append(armor.get()).append(" ").append(GearType.ARMOR.formatName()).append(" EXP\n");
                gain.append("+").append(extra.get()).append(" ").append(GearType.EXTRA.formatName()).append(" EXP");
            } else {
                Events.OBTAIN_EXP_GEAR.invoke(new EventObtainExpGear(user, guild, weapon, Ref.of(type)));
                gain.append("+").append(weapon.get()).append(" ").append(type.formatName()).append(" EXP");
            }
            Events.TRAIN.invoke(new EventTrain(user, guild, all ? null : type));
            ctx.reply(Embeds.small("Train").description(new String(gain)).build()).send();
        } else {
            ctx.reply(Embeds.error("You can train again " + Timestamps.toTimestamp(TimeFormat.RELATIVE, user.canTrainAt()) + ".").build()).send();
        }
    }
}
