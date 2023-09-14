package com.stanuwu.cdlegacy.db;

public class DBInfoException extends RuntimeException {
    public DBInfoException() {
        super("Unable to read database info.");
    }
}
