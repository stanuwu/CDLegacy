package com.stanuwu.cdlegacy.features.command;

import com.stanuwu.cdlegacy.message.ReplyContext;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.Nullable;

public class CommandContext {
    private final CommandData command;
    private final SlashCommandInteractionEvent event;

    public CommandContext(CommandData command, SlashCommandInteractionEvent event) {
        this.command = command;
        this.event = event;
    }

    public ReplyContext reply() {
        return new ReplyContext(command.slowCommand(), event);
    }

    public ReplyContext reply(MessageEmbed embed) {
        return new ReplyContext(command.slowCommand(), event).embeds(embed);
    }

    public ReplyContext reply(String text) {
        return new ReplyContext(command.slowCommand(), event).text(text);
    }

    @Nullable
    public String getArg(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsString();
        return null;
    }

    @Nullable
    public Integer getArgInt(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsInt();
        return null;
    }

    @Nullable
    public Long getArgLong(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsLong();
        return null;
    }

    @Nullable
    public Double getArgDouble(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsDouble();
        return null;
    }

    @Nullable
    public Message.Attachment getArgAttachment(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsAttachment();
        return null;
    }

    @Nullable
    public Channel getArgChannel(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsChannel();
        return null;
    }

    @Nullable
    public Member getArgMember(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsMember();
        return null;
    }

    @Nullable
    public IMentionable getArgMentionable(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsMentionable();
        return null;
    }

    @Nullable
    public Role getArgRole(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsRole();
        return null;
    }

    @Nullable
    public User getArgUser(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsUser();
        return null;
    }

    public long authorId() {
        return event.getUser().getIdLong();
    }
}
