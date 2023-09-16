package com.stanuwu.cdlegacy.features.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandData {
    String name();

    String description() default "This is a CDLegacy command.";

    boolean slowCommand() default false;

    boolean staffCommand() default false;

    boolean isGame() default false;

    boolean guildOnly() default true;
}
