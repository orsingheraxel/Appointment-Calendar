package com.appointment.diary.c_domain.exception;

//example --> invalid date to register appointment
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {super(message);}
}
