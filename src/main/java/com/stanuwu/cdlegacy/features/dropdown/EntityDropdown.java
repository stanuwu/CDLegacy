package com.stanuwu.cdlegacy.features.dropdown;

import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;

public abstract class EntityDropdown extends BaseDropdown {
    @Override
    protected void propagateEvent(GenericSelectMenuInteractionEvent<?, ?> event, long ownerId, String route) {
        doSelect(new EntityDropdownContext((EntitySelectInteractionEvent) event, this.data, ownerId, route));
    }

    protected abstract void doSelect(EntityDropdownContext ctx);
}
