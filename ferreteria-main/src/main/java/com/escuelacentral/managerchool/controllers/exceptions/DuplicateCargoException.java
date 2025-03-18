package com.escuelacentral.managerchool.controllers.exceptions;

public class DuplicateCargoException extends RuntimeException{
    public DuplicateCargoException(String message) {
        super(message);
    }
}
