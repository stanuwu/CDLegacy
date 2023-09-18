package com.stanuwu.cdlegacy.features.command;

import com.stanuwu.cdlegacy.game.data.DBData;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.ReplyContext;
import lombok.Getter;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.helpers.CheckReturnValue;

import java.time.LocalDateTime;

public class CommandContext {
    private final CommandData command;
    private final SlashCommandInteractionEvent event;
    @Getter
    private final DBUser user;
    @Getter
    private final DBGuild guild;

    public CommandContext(CommandData command, SlashCommandInteractionEvent event) {
        this.command = command;
        this.event = event;
        if (command.isGame()) {
            this.user = DBData.getUser(event.getUser().getIdLong());
            if (event.getGuild() != null) this.guild = DBData.getGuild(event.getGuild().getIdLong());
            else this.guild = null;
        } else {
            this.user = null;
            this.guild = null;
        }
    }

    @CheckReturnValue
    public ReplyContext reply() {
        return new ReplyContext(command.slowCommand(), event);
    }

    @CheckReturnValue
    public ReplyContext reply(MessageEmbed embed) {
        return new ReplyContext(command.slowCommand(), event).embeds(embed);
    }

    @CheckReturnValue
    public ReplyContext reply(String text) {
        return new ReplyContext(command.slowCommand(), event).text(text);
    }

    @CheckReturnValue
    public ReplyContext message() {
        return new ReplyContext(true, event);
    }

    @CheckReturnValue
    public ReplyContext message(MessageEmbed embed) {
        return new ReplyContext(true, event).embeds(embed);
    }

    @CheckReturnValue
    public ReplyContext message(String text) {
        return new ReplyContext(true, event).text(text);
    }

    public String getArg(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsString();
        return null;
    }

    public Integer getArgInt(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsInt();
        return null;
    }

    public Long getArgLong(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsLong();
        return null;
    }

    public Double getArgDouble(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsDouble();
        return null;
    }

    public Message.Attachment getArgAttachment(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsAttachment();
        return null;
    }

    public Channel getArgChannel(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsChannel();
        return null;
    }

    public Member getArgMember(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsMember();
        return null;
    }

    public IMentionable getArgMentionable(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsMentionable();
        return null;
    }

    public Role getArgRole(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsRole();
        return null;
    }

    public User getArgUser(String argName) {
        OptionMapping option = event.getOption(argName);
        if (option != null) return option.getAsUser();
        return null;
    }

    public long authorId() {
        return event.getUser().getIdLong();
    }

    public User author() {
        return event.getUser();
    }

    public Guild guild() {
        return event.getGuild();
    }

    public LocalDateTime time() {
        return event.getTimeCreated().toLocalDateTime();
    }
}
