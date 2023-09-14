package com.stanuwu.cdlegacy.features.button;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Duration;

public abstract class BaseButton extends ListenerAdapter {
    private final ButtonData data;

    public BaseButton() {
        this.data = this.getClass().getAnnotation(ButtonData.class);
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String id = event.getButton().getId();
        if (id == null) return;
        if (data.maxAgeMinutes() > 0 && Duration.between(event.getMessage().getTimeCreated().toLocalDateTime(), event.getTimeCreated().toLocalDateTime()).toMinutes() > data.maxAgeMinutes()) {
            event.reply("Error: This interaction is expired.").setEphemeral(true).queue();
            return;
        }
        String[] args = id.split(";");
        if (args.length < (data.complex() ? 3 : 2)) throw new InvalidButtonIdException(event.getButton().getId());
        if (!args[0].equals(this.data.name())) return;
        long ownerId = Long.parseLong(args[1]);
        if (this.data.ownerOnly() && event.getUser().getIdLong() != ownerId) {
            event.reply("Error: You do not have access to this interaction.").setEphemeral(true).queue();
            return;
        }
        String route = this.data.complex() ? args[2] : "";
        if (this.data.slow()) event.deferReply().queue();
        if (this.data.slow()) event.deferEdit().queue();
        doButton(new ButtonContext(event, this.data, ownerId, route));
    }

    protected abstract void doButton(ButtonContext ctx);
}
