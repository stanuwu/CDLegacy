package com.stanuwu.cdlegacy;

import com.stanuwu.cdlegacy.db.DB;
import com.stanuwu.cdlegacy.features.button.impl.TestButton;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.impl.TestCommand;
import com.stanuwu.cdlegacy.features.command.impl.TestDBCommand;
import com.stanuwu.cdlegacy.features.dropdown.impl.TestEntityDropdown;
import com.stanuwu.cdlegacy.features.dropdown.impl.TestStringDropdown;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Features {
    ListenerAdapter[] commands;

    public Features(DB database) {
        commands = new ListenerAdapter[]{
                new TestCommand(),
                new TestDBCommand(database),
                new TestButton(),
                new TestStringDropdown(),
                new TestEntityDropdown(),
        };
    }

    public void registerPre(JDABuilder b) {
        b.addEventListeners((Object[]) commands);
    }

    public void registerPost(JDA jda, boolean modeDev, long devGuild) {
        for (ListenerAdapter l : commands) {
            if (l instanceof BaseCommand c) {
                if (modeDev) {
                    Guild guild = jda.getGuildById(devGuild);
                    if (guild != null) guild.upsertCommand(c.buildCommand()).complete();
                } else {
                    jda.upsertCommand(c.buildCommand()).complete();
                }
            }
        }
    }
}
