package com.escuelacentral.managerchool.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        if (exception.getMessage().contains("Usuario no encontrado con email: ")) {
            errorMessage = exception.getMessage();
        } else if (exception.getMessage().contains("Bad credentials")) {
            errorMessage = "Revise sus credenciales.";
        } else if (exception.getMessage().contains("User account is locked")) {
            errorMessage = "Usuario con cuenta Bloqueada";
        } else if (exception.getMessage().contains("User account has expired")) {
            errorMessage = "La cuenta de usuario ha expirado";
        } else if (exception.getMessage().contains("User credentials have expired")) {
            errorMessage = "Las credenciales de usuario han expirado";
        } else {
            errorMessage = "Error de autenticacion.";
        }
        response.sendRedirect("/login?error=" + errorMessage);

        /*if (exception instanceof UsernameNotFoundException) {
            response.sendRedirect("/login?error=usuarioNoRegistrado");
        } else if (exception instanceof BadCredentialsException) {
            response.sendRedirect("/login?error=credencialesInvalidas");
        } else {
            response.sendRedirect("/login?error=errorDesconocido");
        }*/
    }
}
