package com.clementlee.bugtrackerapi.exceptions;

public class IssueNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 7;

    public IssueNotFoundException(String message){
        super(message);
    }

}
