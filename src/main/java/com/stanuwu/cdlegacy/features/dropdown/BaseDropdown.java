package com.stanuwu.cdlegacy.features.dropdown;

import com.stanuwu.cdlegacy.features.IFeature;
import com.stanuwu.cdlegacy.features.ParamCache;
import com.stanuwu.cdlegacy.game.data.DBData;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public abstract class BaseDropdown extends ListenerAdapter implements IFeature {
    protected final DropdownData data;

    public BaseDropdown() {
        this.data = this.getClass().getAnnotation(DropdownData.class);
    }

    @Override
    public void onEntitySelectInteraction(@NotNull EntitySelectInteractionEvent event) {
        handleSelectInteractionEvent(event);
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        handleSelectInteractionEvent(event);
    }

    private void handleSelectInteractionEvent(GenericSelectMenuInteractionEvent<?, ?> event) {
        String id = event.getSelectMenu().getId();
        if (id == null) return;
        String[] args = id.split(";");
        if (!args[0].equals(this.data.name())) return;
        if (args.length < (this.data.complex() ? 3 : 2))
            throw new InvalidDropdownIdException(event.getSelectMenu().getId());
        if (this.data.guildOnly() && event.getGuild() == null) {
            event.reply("Error: This interaction can not be used in dms.").setEphemeral(true).queue();
            return;
        }
        boolean expired = this.data.maxAgeMinutes() > 0 && Duration.between(event.getMessage().getTimeCreated().toLocalDateTime(), event.getTimeCreated().toLocalDateTime()).toMinutes() > this.data.maxAgeMinutes();
        boolean outdated = this.data.useCache() && !ParamCache.isUpToDate(this.data.name(), event.getMessageIdLong());
        if (expired || outdated) {
            event.reply("Error: This interaction is expired.").setEphemeral(true).queue();
            return;
        }
        long ownerId = Long.parseLong(args[1]);
        if (this.data.ownerOnly() && event.getUser().getIdLong() != ownerId) {
            event.reply("Error: You do not have access to this interaction.").setEphemeral(true).queue();
            return;
        }
        if (this.data.isGame() && DBData.getUser(event.getUser().getIdLong()) == null) {
            event.reply("Error: Please create a character using `/start` to play.").setEphemeral(true).queue();
            return;
        }
        String route = this.data.complex() ? args[2] : "";
        if (this.data.slow()) event.deferReply().queue();
        if (this.data.slow()) event.deferEdit().queue();
        propagateEvent(event, ownerId, route);
    }

    protected abstract void propagateEvent(GenericSelectMenuInteractionEvent<?, ?> event, long ownerId, String route);

    @Override
    public String getName() {
        return this.data.name();
    }
}
