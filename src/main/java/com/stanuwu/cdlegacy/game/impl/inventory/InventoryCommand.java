package com.stanuwu.cdlegacy.game.impl.inventory;

import com.stanuwu.cdlegacy.features.command.BaseCommand;
import com.stanuwu.cdlegacy.features.command.CommandContext;
import com.stanuwu.cdlegacy.features.command.CommandData;
import com.stanuwu.cdlegacy.game.data.DBInv;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@CommandData(name = "inventory", description = "View the items in your inventory.", isGame = true)
public class InventoryCommand extends BaseCommand {
    @Override
    protected void doCommand(CommandContext ctx) {
        DBUser user = ctx.getUser();
        StringBuilder desc = new StringBuilder();
        boolean alternate = false;
        for (DBInv.Entry item : user.getInv().toEntrySetOwned()) {
            alternate = !alternate;
            desc.append(alternate ? ":small_blue_diamond: " : ":small_orange_diamond: ").append(item.amount()).append("x ").append(item.item().getName()).append("\n");
        }
        String description = new String(desc);
        ctx.reply(
                Embeds.small("Inventory")
                        .description(description.length() < 1 ? "*Your inventory is empty.*" : description)
                        .build()
        ).send();
    }
}
