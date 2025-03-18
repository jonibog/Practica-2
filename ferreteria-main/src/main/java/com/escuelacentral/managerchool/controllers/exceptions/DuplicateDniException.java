package com.escuelacentral.managerchool.controllers.exceptions;

public class DuplicateDniException extends RuntimeException{
    public DuplicateDniException(String message) {
        super(message);
    }
}
