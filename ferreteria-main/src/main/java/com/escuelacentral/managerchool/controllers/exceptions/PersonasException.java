package com.escuelacentral.managerchool.controllers.exceptions;

public class PersonasException {
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class DniAlreadyExistsException extends RuntimeException {
        public DniAlreadyExistsException(String message) {
            super(message);
        }
    }
}
