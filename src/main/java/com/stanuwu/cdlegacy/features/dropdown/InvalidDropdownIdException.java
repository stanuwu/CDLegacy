package com.stanuwu.cdlegacy.features.dropdown;

public class InvalidDropdownIdException extends RuntimeException {
    public InvalidDropdownIdException(String id) {
        super("Dropdown with id " + id + " is missing parameters.");
    }
}
