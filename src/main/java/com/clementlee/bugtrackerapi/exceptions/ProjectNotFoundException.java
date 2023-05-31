package com.clementlee.bugtrackerapi.exceptions;

public class ProjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 3;
    public ProjectNotFoundException(String message){
        super(message);
    }
}
