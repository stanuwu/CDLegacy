package com.stanuwu.cdlegacy.features.dropdown;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DropdownData {
    String name();

    int maxAgeMinutes() default -1;

    boolean ownerOnly() default true;

    boolean complex() default false;

    boolean slow() default false;
}
