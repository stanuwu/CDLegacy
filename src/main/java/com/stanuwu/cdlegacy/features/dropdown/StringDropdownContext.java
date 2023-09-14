package com.stanuwu.cdlegacy.features.dropdown;

import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;

import java.util.List;
import java.util.stream.Collectors;

public class StringDropdownContext extends DropdownContext {
    public StringDropdownContext(StringSelectInteractionEvent event, DropdownData dropdown, long ownerId, String route) {
        super(event, dropdown, ownerId, route);
    }

    public List<String> getValues() {
        return ((StringSelectInteractionEvent) this.event).getSelectedOptions().stream().map(SelectOption::getValue).collect(Collectors.toList());
    }

    public String getValue() {
        return ((StringSelectInteractionEvent) this.event).getSelectedOptions().get(0).getValue();
    }
}
