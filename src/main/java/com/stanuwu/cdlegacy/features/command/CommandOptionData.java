package com.stanuwu.cdlegacy.features.command;

import lombok.Getter;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public final class CommandOptionData {
    @Getter
    private final OptionType type;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final boolean required;
    @Getter
    private final CommandOptionChoice[] choices;

    public CommandOptionData(OptionType type, String name, String description, boolean required,
                             CommandOptionChoice[] choices) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
        this.choices = choices;
    }

    public CommandOptionData(OptionType type, String name, String description, boolean required) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
        this.choices = new CommandOptionChoice[]{};
    }
}
