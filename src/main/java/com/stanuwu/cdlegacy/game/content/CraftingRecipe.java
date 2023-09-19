package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.data.DBInv;
import lombok.Getter;

public enum CraftingRecipe {
    STICK(Weapon.STICK, new DBInv.Entry(Item.WOOD, 10)),
    RAGS(Armor.RAGS, new DBInv.Entry(Item.LEATHER, 10)),
    PENDANT(Extra.PENDANT, new DBInv.Entry(Item.STONE, 10)),
    WOODEN_SWORD(Weapon.WOODEN_SWORD, new DBInv.Entry(Item.WOOD, 10)),
    FISH_SCALE_MAIL(Armor.FISH_SCALE_MAIL, new DBInv.Entry(Item.FISH_SCALE, 25)),
    WOLF_BLADE(Weapon.WOLF_BLADE, new DBInv.Entry(Item.WOLF_TOOTH, 5)),
    GOLD_PLATED_ARMOR(Armor.GOLD_PLATED_ARMOR, new DBInv.Entry(Item.GOLDEN_LEAF, 5)),
    DIAMOND_TIP_SWORD(Weapon.DIAMOND_TIP_SWORD, new DBInv.Entry(Item.STONE, 1000), new DBInv.Entry(Item.DIAMOND, 10)),
    HYDRA_SCALE_MAIL(Armor.HYDRA_SCALE_MAIL, new DBInv.Entry(Item.FISH_SCALE, 500), new DBInv.Entry(Item.HYDRA_SCALE, 10)),

    FIRE_ORB(Extra.FIRE_ORB, new DBInv.Entry(Item.ASHES, 5000)),
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
