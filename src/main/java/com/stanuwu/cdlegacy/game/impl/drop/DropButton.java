package com.stanuwu.cdlegacy.game.impl.drop;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;
import com.stanuwu.cdlegacy.game.content.*;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBUser;
import com.stanuwu.cdlegacy.message.embeds.Embeds;

@ButtonData(name = "drop-button", isGame = true, maxAgeMinutes = 15, complex = true, useCache = true)
public class DropButton extends BaseButton {
    @Override
    protected void doButton(ButtonContext ctx) {
        ctx.disableAll();
        DBUser user = ctx.getUser();
        GearType type = DBEnum.fromKey(ctx.getCache().getString("type"), GearType.class);
        String g = ctx.getCache().getString("gear");
        Rarity rarity = DBEnum.fromKey(ctx.getCache().getString("rarity"), Rarity.class);
        switch (ctx.getRoute()) {
            case "claim" -> {
                IGear gear = switch (type) {
                    case WEAPON -> {
                        Weapon weapon = DBEnum.fromKey(g, Weapon.class);
                        user.getWeapon().set(weapon);
                        yield weapon;
                    }
                    case ARMOR -> {
                        Armor armor = DBEnum.fromKey(g, Armor.class);
                        user.getArmor().set(armor);
                        yield armor;
                    }
                    case EXTRA -> {
                        Extra extra = DBEnum.fromKey(g, Extra.class);
                        user.getExtra().set(extra);
                        yield extra;
                    }
                };
                ctx.reply(Embeds.small("Claim").description(":white_check_mark: Claimed " + gear.getName() + ".").build()).send();
            }
            case "sell" -> {
                user.addCoins(rarity.getSellPrice());
                ctx.reply(Embeds.small("Sell").description(":coin: +" + rarity.getSellPrice() + " coins").build()).send();
            }
            case "infuse" -> {
                type.addExp(user, rarity.getInfuseValue());
                ctx.reply(Embeds.small("Infuse").description(":diamond_shape_with_a_dot_inside: +" + rarity.getInfuseValue() + " " + type.formatName() + " EXP").build()).send();
            }
        }
    }
}
