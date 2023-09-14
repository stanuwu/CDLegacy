package com.stanuwu.cdlegacy.features.dropdown.impl;

import com.stanuwu.cdlegacy.features.dropdown.DropdownData;
import com.stanuwu.cdlegacy.features.dropdown.EntityDropdown;
import com.stanuwu.cdlegacy.features.dropdown.EntityDropdownContext;

@DropdownData(name = "e-test-dropdown")
public class TestEntityDropdown extends EntityDropdown {
    @Override
    protected void doSelect(EntityDropdownContext ctx) {
        ctx.disable();
        ctx.reply(ctx.getValueRole().getAsMention()).send();
    }
}
