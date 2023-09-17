package com.stanuwu.cdlegacy.game.event;

import java.util.function.Function;

public class Ref<T> {

    public static <T> Ref<T> of(T value) {
        return new Ref<>(value);
    }

    private T value;

    public Ref(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return this.value;
    }

    public void op(Function<T, T> function) {
        this.value = function.apply(this.value);
    }
}
