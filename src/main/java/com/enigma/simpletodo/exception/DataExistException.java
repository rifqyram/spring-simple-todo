package com.enigma.simpletodo.exception;

public class DataExistException extends RuntimeException {
    public DataExistException(String message) {
        super(message + " already exist");
    }
}
