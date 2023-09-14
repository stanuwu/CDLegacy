package com.stanuwu.cdlegacy.features.button;

import com.stanuwu.cdlegacy.message.InteractionReplyContext;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;

import java.util.ArrayList;
import java.util.List;

public class ButtonContext {
    private final ButtonInteractionEvent event;
    private final ButtonData button;
    @Getter
    private final long ownerId;
    @Getter
    private final String route;

    public ButtonContext(ButtonInteractionEvent event, ButtonData button, long ownerId, String route) {
        this.event = event;
        this.button = button;
        this.ownerId = ownerId;
        this.route = route;
    }

    public InteractionReplyContext reply() {
        return new InteractionReplyContext(button.slow(), event);
    }

    public InteractionReplyContext reply(MessageEmbed embed) {
        return new InteractionReplyContext(button.slow(), event).embeds(embed);
    }

    public InteractionReplyContext reply(String text) {
        return new InteractionReplyContext(button.slow(), event).text(text);
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
}
