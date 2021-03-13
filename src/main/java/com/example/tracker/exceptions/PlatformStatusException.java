package com.example.tracker.exceptions;

public class PlatformStatusException extends RuntimeException{
    public PlatformStatusException (String message){
        super(message);
    }
}
