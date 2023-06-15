package com.clementlee.bugtrackerapi.exceptions;

public class UserNotInIssueException extends RuntimeException{

    private static final long serialVersionUID = 17;

    public UserNotInIssueException(String message){
        super(message);
    }

}
