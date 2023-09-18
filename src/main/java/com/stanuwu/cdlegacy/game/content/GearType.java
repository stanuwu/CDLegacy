package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.data.DBUser;
import lombok.Getter;

public enum GearType {
    WEAPON("Weapon", ":dagger:", Weapon.class),
    ARMOR("Armor", ":shield:", Armor.class),
    EXTRA("Extra", ":crystal_ball:", Extra.class),
    ;
    @Getter
    private final String name;
    @Getter
    private final String emoji;
    @Getter
    private final Class<?> type;

    GearType(String name, String emoji, Class<?> type) {
        this.name = name;
        this.emoji = emoji;
        this.type = type;
    }

    public void addExp(DBUser user, int exp) {
        switch (this) {
            case WEAPON -> user.getWeapon().addExp(exp);
            case ARMOR -> user.getArmor().addExp(exp);
            case EXTRA -> user.getExtra().addExp(exp);
        }
    }

    public String formatName() {
        return this.getEmoji() + " " + this.getName();
    }
}
