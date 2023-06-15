package com.clementlee.bugtrackerapi.exceptions;

public class IssueCommentNotInThisIssueException extends RuntimeException{

    private static final long serialVersionUID = 14;

    public IssueCommentNotInThisIssueException(String message){
        super(message);
    }

}
