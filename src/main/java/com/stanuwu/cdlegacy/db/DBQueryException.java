package com.stanuwu.cdlegacy.db;

public class DBQueryException extends RuntimeException {
    public DBQueryException(String file) {
        super("Unable to load query " + file + ".");
    }
}
