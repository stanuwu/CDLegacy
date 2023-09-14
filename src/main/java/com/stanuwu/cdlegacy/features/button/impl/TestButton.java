package com.stanuwu.cdlegacy.features.button.impl;

import com.stanuwu.cdlegacy.features.button.BaseButton;
import com.stanuwu.cdlegacy.features.button.ButtonContext;
import com.stanuwu.cdlegacy.features.button.ButtonData;

@ButtonData(name = "test-button", complex = true, maxAgeMinutes = 15)
public class TestButton extends BaseButton {

    @Override
    public void doButton(ButtonContext ctx) {
        ctx.disableAll();
        switch (ctx.getRoute()) {
            case "send" -> ctx.reply("Message Reply").send();
            case "edit" -> ctx.reply("Message Edit").edit();
            default -> ctx.reply("Invalid Route").hidden().send();
        }
    }
}
