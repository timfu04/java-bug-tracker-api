package com.clementlee.bugtrackerapi.exceptions;

public class RoleNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1;

    public RoleNotFoundException(String message){
        super(message);
    }

}
