package com.clementlee.bugtrackerapi.exceptions;

public class UserNotInProjectException extends RuntimeException{

    private static final long serialVersionUID = 11;

    public UserNotInProjectException(String message){
        super(message);
    }

}
