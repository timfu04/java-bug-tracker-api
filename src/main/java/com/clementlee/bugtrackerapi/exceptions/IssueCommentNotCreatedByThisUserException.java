package com.clementlee.bugtrackerapi.exceptions;

public class IssueCommentNotCreatedByThisUserException extends RuntimeException{

    private static final long serialVersionUID = 15;

    public IssueCommentNotCreatedByThisUserException(String message){
        super(message);
    }

}
