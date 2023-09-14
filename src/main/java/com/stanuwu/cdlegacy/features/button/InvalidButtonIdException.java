package com.stanuwu.cdlegacy.features.button;

public class InvalidButtonIdException extends RuntimeException {
    public InvalidButtonIdException(String id) {
        super("Button with id " + id + " is missing parameters.");
    }
}
