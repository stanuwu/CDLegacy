package com.stanuwu.cdlegacy.features.dropdown;

import lombok.Getter;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

@SuppressWarnings("unchecked")
public class Dropdowns {
    public static EntitySelectMenu.SelectTarget USER = EntitySelectMenu.SelectTarget.USER;
    public static EntitySelectMenu.SelectTarget ROLE = EntitySelectMenu.SelectTarget.ROLE;
    public static EntitySelectMenu.SelectTarget CHANNEL = EntitySelectMenu.SelectTarget.CHANNEL;

    public static EntityD entity(String id, long ownerId, String placeholder, EntitySelectMenu.SelectTarget type) {
        return new EntityD(id, ownerId, placeholder, type);
    }

    public static StringD string(String id, long ownerId, String placeholder, Option... options) {
        return new StringD(id, ownerId, placeholder, options);
    }

    public static Option option(String label, String value) {
        return new Option(label, value);
    }

    public static abstract class Base<T extends Base<?>> {
        protected final String id;
        protected long ownerId;
        protected int min = 1;
        protected int max = 1;
        protected String placeholder;
        protected String route = "";

        public Base(String id, long ownerId, String placeholder) {
            this.id = id;
            this.ownerId = ownerId;
        }

        public T min(int min) {
            this.min = min;
            return (T) this;
        }

        public T max(int max) {
            this.max = max;
            return (T) this;
        }

        public T route(String route) {
            this.route = route;
            return (T) this;
        }

        protected String makeId() {
            return new String(new StringBuilder(this.id).append(";").append(this.ownerId).append(";").append(this.route));
        }
    }

    public static class EntityD extends Base<EntityD> {
        private final EntitySelectMenu.SelectTarget type;

        public EntityD(String id, long ownerId, String placeholder, EntitySelectMenu.SelectTarget type) {
            super(id, ownerId, placeholder);
            this.type = type;
        }

        public EntitySelectMenu build() {
            return EntitySelectMenu.create(this.makeId(), this.type)
                    .setMinValues(this.min)
                    .setMaxValues(this.max)
                    .setPlaceholder(this.placeholder)
                    .build();
        }
    }

    public static class StringD extends Base<StringD> {
        private final Option[] options;

        public StringD(String id, long ownerId, String placeholder, Option... options) {
            super(id, ownerId, placeholder);
            this.options = options;
        }

        public StringSelectMenu build() {
            StringSelectMenu.Builder menu = StringSelectMenu.create(this.makeId())
                    .setMinValues(this.min)
                    .setMaxValues(this.max)
                    .setPlaceholder(this.placeholder);
            for (Option o : options) {
                SelectOption option = SelectOption.of(o.getLabel(), o.getValue());
                if (o.getDescription() != null) option = option.withDescription(o.getDescription());
                if (o.getEmoji() != null) option = option.withEmoji(o.getEmoji());
                option = option.withDefault(o.isTicked());
                menu.addOptions(option);
            }
            return menu.build();
        }
    }

    public static class Option {
        @Getter
        private final String label;
        @Getter
        private final String value;
        @Getter
        private String description = null;
        @Getter
        private Emoji emoji = null;
        @Getter
        private boolean ticked = false;

        public Option(String label, String value) {
            this.label = label;
            this.value = value;
        }

        public Option desc(String description) {
            this.description = description;
            return this;
        }

        public Option emoji(Emoji emoji) {
            this.emoji = emoji;
            return this;
        }

        public Option ticked() {
            this.ticked = true;
            return this;
        }
    }
}
