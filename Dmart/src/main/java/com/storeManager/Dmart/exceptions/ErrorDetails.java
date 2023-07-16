package com.storeManager.Dmart.exceptions;


import java.time.LocalDateTime;


//This class is created to give a systamatic manner to throw exception.So that user able to know
// main reason of exception and handle exception acording to their need.
public class ErrorDetails {

    private LocalDateTime timestamp;

    private String message;

    private String details;

    ErrorDetails(LocalDateTime timestamp , String message ,String details){
        this.details=details;
        this.message=message;
        this.timestamp=timestamp;
    }

}
