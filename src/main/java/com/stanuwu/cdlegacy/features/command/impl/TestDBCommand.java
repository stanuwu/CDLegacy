package com.stanuwu.cdlegacy.features.command.impl;

import com.stanuwu.cdlegacy.db.DB;
import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;

@CommandData(name = "testdb", staffCommand = true)
public class TestDBCommand extends BaseCommand {
    private final DB database;

    public TestDBCommand(DB database) {
        this.database = database;
    }

    @Override
    protected void doCommand(CommandContext ctx) {
        ctx.reply(database.test()).send();
    }
}
