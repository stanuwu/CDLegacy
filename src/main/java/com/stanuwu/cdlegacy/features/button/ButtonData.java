package com.stanuwu.cdlegacy.features.button;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ButtonData {
    String name();

    int maxAgeMinutes() default -1;

    boolean ownerOnly() default true;

    boolean complex() default false;

    boolean slow() default false;

    boolean useCache() default false;

    boolean isGame() default false;

    boolean guildOnly() default true;
}
