package com.stanuwu.cdlegacy.game.impl.quest;

import com.stanuwu.cdlegacy.features.button.Buttons;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

@CommandData(name = "quest", description = "View your active quest or accept new quests.", isGame = true)
public class QuestCommand extends BaseCommand {
    public static MessageEmbed getEmbed(DBUser user) {
        if (user.getQuest().getType().isNone()) {
            return Embeds.small("Quest").description(":no_entry_sign: No active quest.").build();
        } else if (user.getQuest().isDone()) {
            return Embeds.small("Quest").description(":white_check_mark: Quest completed.").build();
        } else {
            return Embeds.small("Quest").description(":notepad_spiral: " + user.getQuest().desc()).build();
        }
    }

    public static Button getButton(DBUser user) {
        if (user.getQuest().getType().isNone()) {
            return Buttons.of(
                    "quest-button",
                    user.getUserId(),
                    "New Quest"
            ).style(ButtonStyle.SUCCESS).route("new").build();
        } else if (user.getQuest().isDone()) {
            return Buttons.of(
                    "quest-button",
                    user.getUserId(),
                    "Claim Rewards"
            ).style(ButtonStyle.SUCCESS).route("claim").build();
        } else {
            return Buttons.of(
                    "quest-button",
                    user.getUserId(),
                    "Cancel Quest"
            ).style(ButtonStyle.DANGER).route("cancel").build();
        }
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        ctx.reply(getEmbed(user)).actionRow(getButton(user)).send();
    }
}
