package com.appointment.diary.c_domain.exception;

//example --> repeated email
public class DuplicateResourceException  extends RuntimeException{

    public DuplicateResourceException(String message){super(message);}

    public DuplicateResourceException(String resourceName, String field, String value) {
        super(resourceName + " with " + field + " " + value + " already exists.");
    }
}
