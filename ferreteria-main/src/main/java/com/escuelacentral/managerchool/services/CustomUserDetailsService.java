package com.escuelacentral.managerchool.services;


import com.escuelacentral.managerchool.controllers.exceptions.PersonasException;
import com.escuelacentral.managerchool.models.PersonasModels;
import com.escuelacentral.managerchool.repositories.IPersonasRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service // Anotamos como servicio de Spring para que pueda ser inyectada
public class CustomUserDetailsService implements UserDetailsService {

    // Aquí puedes inyectar el repositorio para consultar tu base de datos de usuarios
    private final IPersonasRepository usuarioRepository;

    // Constructor donde inyectamos el repositorio
    public CustomUserDetailsService(IPersonasRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Sobrescribimos el método loadUserByUsername, que busca el usuario por su email (nombre de usuario)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca al usuario en la base de datos por el email
        PersonasModels usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new PersonasException.EmailAlreadyExistsException("Usuario no encontrado con email: " + email));

        List<SimpleGrantedAuthority>authorityList=new ArrayList<>();
        usuario.getRoles()
                .forEach(cargo->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(cargo.getAuthority()))));

        // Aquí debes mapear el objeto `Usuario` a un `UserDetails`
        return new User(usuario.getEmail(), usuario.getPassword(),true, usuario.isAccountNonExpired(), usuario.isCredentialsNonExpired(), usuario.isAccountNonLocked(), authorityList);
    }
}