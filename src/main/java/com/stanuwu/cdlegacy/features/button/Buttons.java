package com.stanuwu.cdlegacy.features.button;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

public class Buttons {
    private ButtonStyle style = ButtonStyle.PRIMARY;
    private final String id;
    private final long ownerId;
    private String route = "";
    private String label = null;
    private Emoji emoji = null;

    public Buttons(String id, long ownerId) {
        this.id = id;
        this.ownerId = ownerId;
    }

    private Buttons label(String label) {
        this.label = label;
        return this;
    }

    private Buttons emoji(Emoji emoji) {
        this.emoji = emoji;
        return this;
    }

    public static Buttons of(String id, long ownerId, String label) {
        return new Buttons(id, ownerId).label(label);
    }

    public static Buttons of(String id, long ownerId, Emoji emoji) {
        return new Buttons(id, ownerId).emoji(emoji);
    }

    public static Buttons of(String id, long ownerId, String label, Emoji emoji) {
        return new Buttons(id, ownerId).label(label).emoji(emoji);
    }

    public Buttons style(ButtonStyle style) {
        this.style = style;
        return this;
    }

    public Buttons route(String route) {
        this.route = route;
        return this;
    }

    public Button build() {
        Button button;
        String id = new String(new StringBuilder(this.id).append(";").append(this.ownerId).append(";").append(this.route));
        if (label == null) {
            button = Button.of(style, id, emoji);
        } else if (emoji == null) {
            button = Button.of(style, id, label);
        } else {
            button = Button.of(style, id, label, emoji);
        }
        return button;
    }
}
