package com.clementlee.bugtrackerapi.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private int statusCode;
    private String message;
    private LocalDateTime timestamp;

}
