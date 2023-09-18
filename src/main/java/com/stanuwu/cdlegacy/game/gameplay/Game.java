package com.stanuwu.cdlegacy.game.gameplay;

import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.game.content.GearType;
import com.stanuwu.cdlegacy.game.content.IGear;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.events.EventGearDrop;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.ReplyContext;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import com.stanuwu.cdlegacy.util.Emojis;
import com.stanuwu.cdlegacy.util.Timestamps;
import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.utils.TimeFormat;

import java.time.LocalDateTime;

@UtilityClass
public class Game {
    public void dropGear(DBUser user, DBGuild guild, ReplyContext reply, IGear gear, LocalDateTime time) {
        GearType type = GearType.fromGear(gear);
        Events.GEAR_DROP.invoke(new EventGearDrop(user, guild, gear));
        reply.embeds(
                        Embeds.small(gear.getRarity().getName() + " Drop")
                                .langDescription("drop-desc",
                                        new Placeholder("type", type.formatName()),
                                        new Placeholder("item", gear.getName()),
                                        new Placeholder("rarity", gear.getRarity().getName()),
                                        new Placeholder("description", gear.getDescription()),
                                        new Placeholder("price", "" + gear.getRarity().getSellPrice()),
                                        new Placeholder("infuse", "" + gear.getRarity().getInfuseValue()),
                                        new Placeholder("ltype", type.getName().toLowerCase()),
                                        new Placeholder("timestamp", Timestamps.toTimestamp(TimeFormat.RELATIVE, time.plusMinutes(15)))
                                )
                                .build()
                )
                .actionRow(
                        Buttons.of(
                                "drop-button",
                                user.getUserId(),
                                "Claim",
                                Emojis.WHITE_CHECK_MARK
                        ).route("claim").build(),
                        Buttons.of(
                                "drop-button",
                                user.getUserId(),
                                "Sell",
                                Emojis.COIN
                        ).route("sell").build(),
                        Buttons.of(
                                "drop-button",
                                user.getUserId(),
                                "Infuse",
                                Emojis.DIAMOND_SHAPE_WITH_A_DOT_INSIDE
                        ).route("infuse").build()
                )
                .send().then(m -> ParamCache.start("drop-button", m.getIdLong())
                        .putString("type", DBEnum.toKey(type))
                        .putString("gear", gear.name())
                        .putString("rarity", DBEnum.toKey(gear.getRarity()))
                        .end());
    }
}
