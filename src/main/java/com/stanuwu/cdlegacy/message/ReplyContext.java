package com.stanuwu.cdlegacy.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateRequest;
import org.slf4j.helpers.CheckReturnValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ReplyContext {
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();
    protected final boolean slow;
    protected final GenericInteractionCreateEvent event;
    protected String message = "";
    protected boolean tts = false;
    protected MessageEmbed[] embeds = {};
    protected FileUpload[] files = {};
    protected boolean silent = false;
    protected boolean hidden = false;
    protected final List<ItemComponent[]> actionRows = new ArrayList<>();

    public ReplyContext(boolean slow, GenericInteractionCreateEvent event) {
        this.slow = slow;
        this.event = event;
    }

    @CheckReturnValue
    public ReplyContext text(String text) {
        this.message = text;
        return this;
    }

    @CheckReturnValue
    public ReplyContext tts() {
        this.tts = true;
        return this;
    }

    @CheckReturnValue
    public ReplyContext embeds(MessageEmbed... embeds) {
        this.embeds = embeds;
        return this;
    }

    @CheckReturnValue
    public ReplyContext files(FileUpload... files) {
        this.files = files;
        return this;
    }

    @CheckReturnValue
    public ReplyContext silent() {
        this.silent = true;
        return this;
    }

    @CheckReturnValue
    public ReplyContext hidden() {
        this.hidden = true;
        return this;
    }

    @CheckReturnValue
    public ReplyContext actionRow(ItemComponent... actionRows) {
        this.actionRows.add(actionRows);
        return this;
    }

    public ReplyFuture send() {
        MessageCreateRequest<?> req;
        if (event instanceof SlashCommandInteractionEvent e) {
            if (slow) req = e.getHook().retrieveOriginal().complete().reply(message);
            else req = e.reply(message).setEphemeral(hidden);
            return doSend(req);
        } else if (event instanceof ButtonInteractionEvent e) {
            if (slow) req = e.getHook().retrieveOriginal().complete().reply(message);
            else req = e.reply(message).setEphemeral(hidden);
            return doSend(req);
        } else if (event instanceof GenericSelectMenuInteractionEvent<?, ?> e) {
            if (slow) req = e.getHook().retrieveOriginal().complete().reply(message);
            else req = e.getHook().sendMessage(message).setEphemeral(hidden);
            return doSend(req);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private ReplyFuture doSend(MessageCreateRequest<?> req) {
        req
                .setTTS(tts)
                .setEmbeds(embeds)
                .setFiles(files)
                .setSuppressedNotifications(silent);
        for (ItemComponent[] row : actionRows) {
            req.addActionRow(row);
        }
        CompletableFuture<Object> hook = new CompletableFuture<>();
        threadPool.submit(() -> {
            try {
                hook.complete(((RestAction<?>) req).complete());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new ReplyFuture(hook);
    }

    public static class ReplyFuture {
        private final CompletableFuture<Object> future;

        private ReplyFuture(CompletableFuture<Object> future) {
            this.future = future;
        }

        public void then(Consumer<Message> action) {
            this.future.whenCompleteAsync((o, e) -> {
                Message message = null;
                if (o instanceof InteractionHook h) message = h.retrieveOriginal().complete();
                if (o instanceof Message m) message = m;
                if (message != null) action.accept(message);
            });
        }
    }
}