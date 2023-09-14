package com.stanuwu.cdlegacy.features.dropdown;

import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;

import java.util.List;

public class EntityDropdownContext extends DropdownContext {
    public EntityDropdownContext(EntitySelectInteractionEvent event, DropdownData dropdown, long ownerId, String route) {
        super(event, dropdown, ownerId, route);
    }

    public Mentions getValueMentions() {
        return ((EntitySelectInteractionEvent) this.event).getMentions();
    }

    public List<User> getValuesUsers() {
        return getValueMentions().getUsers();
    }

    public List<GuildChannel> getValuesChannels() {
        return getValueMentions().getChannels();
    }

    public List<Role> getValuesRoles() {
        return getValueMentions().getRoles();
    }

    public User getValueUser() {
        return getValuesUsers().get(0);
    }

    public Channel getValueChannel() {
        return getValuesChannels().get(0);
    }

    public Role getValueRole() {
        return getValuesRoles().get(0);
    }
}
