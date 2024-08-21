package com.appointment.diary.c_domain.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {super(message); }

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " with ID " + id + " not found.");
    }
}
