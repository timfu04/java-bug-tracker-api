package com.clementlee.bugtrackerapi.exceptions;

public class PriorityNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 6;

    public PriorityNotFoundException(String message){
        super(message);
    }
}
