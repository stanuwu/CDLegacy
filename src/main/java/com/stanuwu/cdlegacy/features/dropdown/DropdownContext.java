package com.stanuwu.cdlegacy.features.dropdown;

import com.stanuwu.cdlegacy.message.InteractionReplyContext;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

import java.util.ArrayList;
import java.util.List;

public abstract class DropdownContext {
    protected final GenericSelectMenuInteractionEvent<?, ?> event;
    private final DropdownData dropdown;
    @Getter
    private final long ownerId;
    @Getter
    private final String route;

    public DropdownContext(GenericSelectMenuInteractionEvent<?, ?> event, DropdownData dropdown, long ownerId, String route) {
        this.event = event;
        this.dropdown = dropdown;
        this.ownerId = ownerId;
        this.route = route;
    }

    public InteractionReplyContext reply() {
        return new InteractionReplyContext(dropdown.slow(), event);
    }

    public InteractionReplyContext reply(MessageEmbed embed) {
        return new InteractionReplyContext(dropdown.slow(), event).embeds(embed);
    }

    public InteractionReplyContext reply(String text) {
        return new InteractionReplyContext(dropdown.slow(), event).text(text);
    }

    public void disable() {
        event.editSelectMenu((SelectMenu) event.getSelectMenu().asDisabled()).complete();
    }

    public void remove() {
        event.editSelectMenu(null).complete();
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