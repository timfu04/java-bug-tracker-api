package com.clementlee.bugtrackerapi.exceptions;

public class StatusNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 4;

    public StatusNotFoundException(String message){
        super(message);
    }
}
