package com.clementlee.bugtrackerapi.exceptions;

public class IssueCommentNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 9;

    public IssueCommentNotFoundException(String message){
        super(message);
    }

}
