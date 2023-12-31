package com.stanuwu.cdlegacy.util;

import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.entities.emoji.Emoji;

@UtilityClass
public class Emojis {
    public final Emoji WHITE_CHECK_MARK = of("U+2705");
    public final Emoji NEGATIVE_SQUARE_CROSS_MARK = of("U+274E");
    public final Emoji NO_ENTRY_SIGN = of("U+1F6AB");
    public final Emoji COIN = of("U+1FA99");
    public final Emoji DIAMOND_SHAPE_WITH_A_DOT_INSIDE = of("U+1F4A0");

    public Emoji of(String emoji) {
        return Emoji.fromUnicode(emoji);
    }
}
