package com.stanuwu.cdlegacy.features.dropdown.impl;

import com.stanuwu.cdlegacy.features.dropdown.DropdownData;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdown;
import com.stanuwu.cdlegacy.features.dropdown.StringDropdownContext;

@DropdownData(name = "s-test-dropdown")
public class TestStringDropdown extends StringDropdown {

    @Override
    protected void doSelect(StringDropdownContext ctx) {
        ctx.disable();
        ctx.reply(ctx.getValue()).send();
    }
}
