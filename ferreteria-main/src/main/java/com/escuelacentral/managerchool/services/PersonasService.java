package com.escuelacentral.managerchool.services;

import com.escuelacentral.managerchool.controllers.exceptions.PersonasException;
import com.escuelacentral.managerchool.controllers.exceptions.registroNoExistenteExcepion;
import com.escuelacentral.managerchool.models.*;
import com.escuelacentral.managerchool.repositories.IPersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonasService implements UserDetailsService {
    @Autowired
    IPersonasRepository iPersonasRepository;

    //************* DEVOLVER
    //por UNO
    @Transactional(readOnly = true)
    public Optional<PersonasModels> findById(int id) {
        return Optional.ofNullable(iPersonasRepository.findById(id)
                .orElseThrow(() -> new registroNoExistenteExcepion("Persona no encontrada con id: " + id)));
    }
    @Transactional(readOnly = true)
    public Optional<PersonasModels>findByEmail(String email){
        Optional<PersonasModels> optionalPersona=iPersonasRepository.findByEmail(email);
        return  optionalPersona;
    }
    @Transactional(readOnly = true)
    public List<PersonasModels> getPersonas(){
        return (List<PersonasModels>) iPersonasRepository.findAllByEnabledTrue();
    }
    //************* ALTA ➕
    public PersonasModels savePersona(PersonasModels pM) throws PersonasException.EmailAlreadyExistsException, PersonasException.DniAlreadyExistsException {
        //pM.setPsw(passwordEncoder.encode(pM.getPsw()));// Encriptar la contraseña antes de guardar
        if(iPersonasRepository.existsByEmail(pM.getEmail())){
            throw new PersonasException.EmailAlreadyExistsException("El email ya está registrado.");
        }

        pM.setAccountNonExpired(true);//(por defecto, sin expiración de cuenta)
        pM.setCredentialsNonExpired(true); //(por defecto, sin expiración de cuenta)
        // Guardar la persona
        PersonasModels usuarioregistrado= iPersonasRepository.save(pM);
        return usuarioregistrado;
    }


    //************* BAJA ❌
    //falta definir Baja de Persona

    //************* MODIFICACION 🔄️
    public PersonasModels modificaPersona(PersonasModels pM){
        return  iPersonasRepository.save(pM);
    }

    //Obtener Permisos
    public Map<String, List<String>> obtenerRolesYPermisos(String email) {
        Optional<PersonasModels> personaOpt = iPersonasRepository.findByEmail(email);
        if (personaOpt.isPresent()) {
            PersonasModels persona = personaOpt.get();
            // Mapa que almacenará los roles como clave y lista de permisos como valor
            Map<String, List<String>> rolesPermisos = new HashMap<>();

            // Recorre los roles de la persona
            for (RolesModels rolEscuela : persona.getRoles()) {
                RolesModels rol = rolEscuela;
                List<String> permisos = rol.getPermisos().stream()
                        .map(PermisosModels::getNombre)
                        .collect(Collectors.toList());
                rolesPermisos.put(rol.getNombre(), permisos);
            }
            return rolesPermisos;
        } else {
            throw new registroNoExistenteExcepion("Usuario no encontrado con email: " + email);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Aquí puedes usar el "email" como equivalente al "username"
        PersonasModels usuario = iPersonasRepository.findByEmail(email)
                .orElseThrow(() -> new registroNoExistenteExcepion("Usuario no encontrado"));

        return User.withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .username(usuario.getEmail())
                .roles("QUe va aca?")
                .build();
    }
}
