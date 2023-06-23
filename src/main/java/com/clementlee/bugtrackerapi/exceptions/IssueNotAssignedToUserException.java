package com.clementlee.bugtrackerapi.exceptions;

public class IssueNotAssignedToUserException extends RuntimeException{

    private static final long serialVersionUID = 13;

    public IssueNotAssignedToUserException(String message){
        super(message);
    }

}
