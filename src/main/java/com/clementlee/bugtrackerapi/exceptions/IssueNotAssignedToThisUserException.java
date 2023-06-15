package com.clementlee.bugtrackerapi.exceptions;

public class IssueNotAssignedToThisUserException extends RuntimeException{

    private static final long serialVersionUID = 13;

    public IssueNotAssignedToThisUserException(String message){
        super(message);
    }

}
