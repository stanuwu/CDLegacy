package com.stanuwu.cdlegacy.game.content;

import com.stanuwu.cdlegacy.game.event.EventHook;

public interface IGear {
    String name();

    String getName();

    String getDescription();

    Rarity getRarity();

    EventHook getEvent();
}
