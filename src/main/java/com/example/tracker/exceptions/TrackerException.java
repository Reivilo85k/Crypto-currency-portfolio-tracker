package com.example.tracker.exceptions;

public class TrackerException extends RuntimeException {
    public TrackerException(String exMessage, Exception exception){
        super(exMessage);
    }

    public TrackerException (String message){
        super(message);
    }
}
