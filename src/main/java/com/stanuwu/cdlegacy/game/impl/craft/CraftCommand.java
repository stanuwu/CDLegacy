package com.stanuwu.cdlegacy.game.impl.craft;

import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.features.dropdown.Dropdowns;
import com.stanuwu.cdlegacy.game.content.CraftingRecipe;
import com.stanuwu.cdlegacy.game.content.GearType;
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

@CommandData(name = "craft", description = "Craft gear from items.", isGame = true)
public class CraftCommand extends BaseCommand {
    public static MessageEmbed getEmbed(CraftingRecipe recipe) {
        return Embeds.small("Craft").langDescription("craft-command",
                new Placeholder("type", GearType.fromGear(recipe.getGear()).formatName()),
                new Placeholder("item", recipe.getGear().getName()),
                new Placeholder("rarity", recipe.getGear().getRarity().getName()),
                new Placeholder("description", recipe.getGear().getDescription()),
                new Placeholder("cost", recipe.formatCost())
        ).build();
    }

    public static StringSelectMenu getDropdown(DBUser user, CraftingRecipe recipe) {
        List<Dropdowns.Option> options = new ArrayList<>();
        for (CraftingRecipe r : CraftingRecipe.values()) {
            Dropdowns.Option option = Dropdowns.option(r.getGear().getName(), DBEnum.toKey(r));
            option.desc(r.formatCost());
            if (r == recipe) option.ticked();
            options.add(option);
        }
        return Dropdowns.string(
                "craft-dropdown",
                user.getUserId(),
                "Select what to craft.",
                options.toArray(new Dropdowns.Option[0])
        ).build();
    }

    public static Button getButton(DBUser user) {
        return Buttons.of(
                "craft-button",
                user.getUserId(),
                "Craft"
        ).style(ButtonStyle.SUCCESS).build();
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        CraftingRecipe def = CraftingRecipe.STICK;
        DBUser user = ctx.getUser();
        ctx.reply(getEmbed(def))
                .actionRow(getDropdown(user, def))
                .actionRow(getButton(user)).send()
                .then(
                        m -> {
                            ParamCache.start("craft-dropdown", m.getIdLong()).putLong("craft-id", m.getIdLong()).end();
                            ParamCache.start("craft-button", m.getIdLong()).putString("recipe", DBEnum.toKey(def)).end();
                        }
                );
    }
}
