package com.stanuwu.cdlegacy.game.impl.quest;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;
import com.stanuwu.cdlegacy.game.content.Quest;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.game.event.Events;
import com.stanuwu.cdlegacy.game.event.Ref;
import com.stanuwu.cdlegacy.game.event.events.EventObtainCoins;
import com.stanuwu.cdlegacy.game.event.events.EventObtainExp;
import com.stanuwu.cdlegacy.message.Placeholder;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@ButtonData(name = "quest-button", maxAgeMinutes = 5, complex = true, isGame = true)
public class QuestButton extends BaseButton {
    @Override
    protected void doButton(ButtonContext ctx) {
        ctx.disable();
        DBUser user = ctx.getUser();
        DBGuild guild = ctx.getGuild();
        switch (ctx.getRoute()) {
            case "claim" -> {
                Ref<Integer> exp = Ref.of(user.getQuest().getExpReward());
                Ref<Integer> coins = Ref.of(user.getQuest().getMoneyReward());
                user.getQuest().clear();
                Events.OBTAIN_EXP.invoke(new EventObtainExp(user, guild, exp));
                Events.OBTAIN_COINS.invoke(new EventObtainCoins(user, guild, coins));
                ctx.message(QuestCommand.getEmbed(user)).actionRow(QuestCommand.getButton(user)).edit();
                ctx.message(Embeds.small("Quest").langDescription("quest-complete",
                        new Placeholder("exp", "" + exp.get()),
                        new Placeholder("coins", "" + coins.get())
                ).build()).send();
            }
            case "new" -> {
                user.getQuest().set(Quest.random());
                ctx.message(QuestCommand.getEmbed(user)).actionRow(QuestCommand.getButton(user)).edit();
            }
            case "cancel" -> {
                user.getQuest().clear();
                ctx.message(QuestCommand.getEmbed(user)).actionRow(QuestCommand.getButton(user)).edit();
            }
        }
    }
}
