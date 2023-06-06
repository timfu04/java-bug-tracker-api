package com.clementlee.bugtrackerapi.exceptions;

public class DuplicateUserInProjectException extends RuntimeException{

    private static final long serialVersionUID = 8;

    public DuplicateUserInProjectException(String message){
        super(message);
    }
}
