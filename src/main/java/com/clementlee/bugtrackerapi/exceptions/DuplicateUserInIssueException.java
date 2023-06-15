package com.clementlee.bugtrackerapi.exceptions;

public class DuplicateUserInIssueException extends RuntimeException{

    private static final long serialVersionUID = 16;

    public DuplicateUserInIssueException(String message){
        super(message);
    }

}
