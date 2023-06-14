package com.clementlee.bugtrackerapi.exceptions;

public class ProjectNotCreatedByThisUserException extends RuntimeException{

    private static final long serialVersionUID = 10;

    public ProjectNotCreatedByThisUserException(String message){
        super(message);
    }

}
