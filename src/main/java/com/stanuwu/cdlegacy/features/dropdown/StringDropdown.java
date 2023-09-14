package com.stanuwu.cdlegacy.features.dropdown;

import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

public abstract class StringDropdown extends BaseDropdown {
    @Override
    protected void propagateEvent(GenericSelectMenuInteractionEvent<?, ?> event, long ownerId, String route) {
        doSelect(new StringDropdownContext((StringSelectInteractionEvent) event, this.data, ownerId, route));
    }

    protected abstract void doSelect(StringDropdownContext ctx);
}
