package com.clementlee.bugtrackerapi.exceptions;

public class IssueNotInThisProjectException extends RuntimeException{

    private static final long serialVersionUID = 12;

    public IssueNotInThisProjectException(String message){
        super(message);
    }

}


