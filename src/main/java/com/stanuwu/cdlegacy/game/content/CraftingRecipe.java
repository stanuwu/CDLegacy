package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.data.DBInv;
import lombok.Getter;

public enum CraftingRecipe {
    STICK(Weapon.STICK, new DBInv.Entry(Item.WOOD, 10)),
    RAGS(Armor.RAGS, new DBInv.Entry(Item.LEATHER, 10)),
    PENDANT(Extra.PENDANT, new DBInv.Entry(Item.STONE, 10)),
    ;
    @Getter
    private final IGear gear;
    @Getter
    private final DBInv.Entry[] cost;

    CraftingRecipe(IGear gear, DBInv.Entry... cost) {
        this.gear = gear;
        this.cost = cost;
    }

    public String formatCost() {
        StringBuilder string = new StringBuilder();
        int i = 0;
        for (DBInv.Entry e : this.cost) {
            i++;
            string.append(e.amount()).append(" ").append(e.item().getName());
            if (i != this.cost.length) string.append(", ");
        }
        return new String(string);
    }
}
