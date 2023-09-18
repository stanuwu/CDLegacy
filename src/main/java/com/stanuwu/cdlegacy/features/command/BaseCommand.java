package com.stanuwu.cdlegacy.features.command;

import com.stanuwu.cdlegacy.Config;
import com.stanuwu.cdlegacy.features.IFeature;
import com.stanuwu.cdlegacy.game.data.DBData;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public abstract class BaseCommand extends ListenerAdapter implements IFeature {
    private final CommandData data;
    private final CommandOptionData[] options;

    public BaseCommand() {
        data = this.getClass().getAnnotation(CommandData.class);
        this.options = new CommandOptionData[]{};
    }

    public BaseCommand(CommandOptionData... options) {
        data = this.getClass().getAnnotation(CommandData.class);
        this.options = options;
    }

    public SlashCommandData buildCommand() {
        SlashCommandData command = Commands.slash(data.name(), data.description());
        for (CommandOptionData o : this.options) {
            OptionData od = new OptionData(o.getType(), o.getName(), o.getDescription(), o.isRequired());
            for (CommandOptionChoice c : o.getChoices()) {
                od.addChoice(c.name(), c.value());
            }
            command.addOptions(od);
        }
        return command;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals(this.data.name())) return;
        if (this.data.staffCommand() && !Config.isStaff(event.getUser().getIdLong())) return;
        if (this.data.guildOnly() && event.getGuild() == null) {
            event.reply("Error: This command can not be used in dms.").setEphemeral(true).queue();
            return;
        }
        if (this.data.isGame() && DBData.getUser(event.getUser().getIdLong()) == null) {
            event.reply("Error: Please create a character using `/start` to play.").setEphemeral(true).queue();
            return;
        }
        if (this.data.slowCommand()) event.deferReply().queue();
        this.doCommand(new CommandContext(this.data, event));
    }

    protected abstract void doCommand(CommandContext ctx);

    @Override
    public String getName() {
        return this.data.name();
    }
}
