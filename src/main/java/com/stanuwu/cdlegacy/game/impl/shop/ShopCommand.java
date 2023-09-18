package com.stanuwu.cdlegacy.game.impl.shop;

import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.dropdown.Dropdowns;
import com.stanuwu.cdlegacy.game.content.ShopEntry;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.util.ArrayList;
import java.util.List;

@CommandData(name = "shop", isGame = true)
public class ShopCommand extends BaseCommand {
    public static MessageEmbed getEmbed(ShopEntry entry) {
        return Embeds.small("Shop").langDescription("shop-command",
                new Placeholder("item", entry.getName()),
                new Placeholder("rarity", entry.getRarity().getName()),
                new Placeholder("description", entry.getDescription()),
                new Placeholder("cost", entry.getPrice() + " coins")
        ).build();
    }

    public static StringSelectMenu getDropdown(DBUser user, ShopEntry entry) {
        List<Dropdowns.Option> options = new ArrayList<>();
        for (ShopEntry e : ShopEntry.values()) {
            Dropdowns.Option option = Dropdowns.option(e.getName(), DBEnum.toKey(e));
            option.desc(e.getPrice() + " coins");
            if (e == entry) option.ticked();
            options.add(option);
        }
        return Dropdowns.string(
                "shop-dropdown",
                user.getUserId(),
                "Select what to buy.",
                options.toArray(new Dropdowns.Option[0])
        ).build();
    }

    public static Button getButton(DBUser user) {
        return Buttons.of(
                "shop-button",
                user.getUserId(),
                "Buy"
        ).style(ButtonStyle.SUCCESS).build();
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        ShopEntry def = ShopEntry.COMMON_CHEST;
        DBUser user = ctx.getUser();
        ctx.reply(getEmbed(def))
                .actionRow(getDropdown(user, def))
                .actionRow(getButton(user)).send()
                .then(
                        m -> {
                            ParamCache.start("shop-dropdown", m.getIdLong()).putLong("shop-id", m.getIdLong()).end();
                            ParamCache.start("shop-button", m.getIdLong()).putString("entry", DBEnum.toKey(def)).end();
                        }
                );
    }
}
