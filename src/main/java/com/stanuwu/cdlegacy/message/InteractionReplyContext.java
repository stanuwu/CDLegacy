package com.stanuwu.cdlegacy.message;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageEditRequest;
import org.slf4j.helpers.CheckReturnValue;

public class InteractionReplyContext extends ReplyContext {
    public InteractionReplyContext(boolean slow, GenericComponentInteractionCreateEvent event) {
        super(slow, event);
    }

    @Override
    @CheckReturnValue
    public InteractionReplyContext text(String text) {
        return (InteractionReplyContext) super.text(text);
    }

    @Override
    @CheckReturnValue
    public InteractionReplyContext tts() {
        return (InteractionReplyContext) super.tts();
    }

    @Override
    @CheckReturnValue
    public InteractionReplyContext embeds(MessageEmbed... embeds) {
        return (InteractionReplyContext) super.embeds(embeds);
    }

    @Override
    @CheckReturnValue
    public InteractionReplyContext files(FileUpload... files) {
        return (InteractionReplyContext) super.files(files);
    }

    @Override
    @CheckReturnValue
    public InteractionReplyContext silent() {
        return (InteractionReplyContext) super.silent();
    }

    @Override
    @CheckReturnValue
    public InteractionReplyContext hidden() {
        return (InteractionReplyContext) super.hidden();
    }

    @Override
    @CheckReturnValue
    public InteractionReplyContext actionRow(ItemComponent... actionRows) {
        return (InteractionReplyContext) super.actionRow(actionRows);
    }

    public void edit() {
        MessageEditRequest<?> req;
        GenericComponentInteractionCreateEvent e = (GenericComponentInteractionCreateEvent) event;
        if (this.slow) req = e.getHook().editOriginal(message);
        else req = e.editMessage(message);
        req
                .setEmbeds(embeds)
                .setFiles(files);
        ActionRow[] rows = new ActionRow[actionRows.size()];
        for (int i = 0; i < actionRows.size(); i++) {
            rows[i] = ActionRow.of(actionRows.get(i));
        }
        req.setComponents(rows);
        ((RestAction<?>) req).queue();
    }
}
