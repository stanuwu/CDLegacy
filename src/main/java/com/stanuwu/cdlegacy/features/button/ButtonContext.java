package com.stanuwu.cdlegacy.features.button;

import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.game.data.DBData;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.InteractionReplyContext;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import org.slf4j.helpers.CheckReturnValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ButtonContext {
    private final ButtonInteractionEvent event;
    private final ButtonData button;
    @Getter
    private final long ownerId;
    @Getter
    private final String route;
    @Getter
    private final ParamCache.CacheObject cache;
    @Getter
    private final DBUser user;
    @Getter
    private final DBGuild guild;

    public ButtonContext(ButtonInteractionEvent event, ButtonData button, long ownerId, String route) {
        this.event = event;
        this.button = button;
        this.ownerId = ownerId;
        this.route = route;
        if (button.useCache()) this.cache = ParamCache.popCache(button.name());
        else this.cache = null;
        if (button.isGame()) {
            this.user = DBData.getUser(event.getUser().getIdLong());
            if (event.getGuild() != null) this.guild = DBData.getGuild(event.getGuild().getIdLong());
            else this.guild = null;
        } else {
            this.user = null;
            this.guild = null;
        }
    }

    @CheckReturnValue
    public InteractionReplyContext reply() {
        return new InteractionReplyContext(button.slow(), event);
    }

    @CheckReturnValue
    public InteractionReplyContext reply(MessageEmbed embed) {
        return new InteractionReplyContext(button.slow(), event).embeds(embed);
    }

    @CheckReturnValue
    public InteractionReplyContext reply(String text) {
        return new InteractionReplyContext(button.slow(), event).text(text);
    }

    @CheckReturnValue
    public InteractionReplyContext message() {
        return new InteractionReplyContext(true, event);
    }

    @CheckReturnValue
    public InteractionReplyContext message(MessageEmbed embed) {
        return new InteractionReplyContext(true, event).embeds(embed);
    }

    @CheckReturnValue
    public InteractionReplyContext message(String text) {
        return new InteractionReplyContext(true, event).text(text);
    }

    public void disable() {
        event.editButton(event.getButton().asDisabled()).complete();
    }

    public void remove() {
        event.editButton(null).complete();
    }

    public void disableAll() {
        List<LayoutComponent> componentList = event.getMessage().getComponents();
        List<LayoutComponent> rep = new ArrayList<>();
        for (LayoutComponent c : componentList) {
            rep.add(c.asDisabled());
        }
        event.getMessage().editMessageComponents(rep).complete();
    }

    public void removeAll() {
        event.getMessage().editMessageComponents().complete();
    }

    public LocalDateTime time() {
        return event.getTimeCreated().toLocalDateTime();
    }
}
