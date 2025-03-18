package com.escuelacentral.managerchool.controllers.exceptions;

import org.springframework.security.core.AuthenticationException;

public class regNoExistenteExcepionSC extends AuthenticationException{
    public regNoExistenteExcepionSC(String message) {
        super(message);
    }
}
