package com.clementlee.bugtrackerapi.exceptions;

public class SeverityNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 5;

    public SeverityNotFoundException(String message){
        super(message);
    }
}
