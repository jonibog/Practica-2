package com.escuelacentral.managerchool.services;


import com.escuelacentral.managerchool.controllers.exceptions.registroNoExistenteExcepion;
import com.escuelacentral.managerchool.models.RolesModels;
import com.escuelacentral.managerchool.repositories.IRolesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolesServices {
    @Autowired
    IRolesRespository iRolesRespository;

    //************* DEVOLVER
    //por UNO
    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre){
        return iRolesRespository.existsByNombre(nombre);
    }
    @Transactional(readOnly = true)
    public RolesModels findById(int id) {
        return iRolesRespository.findById(id)
                .orElseThrow(()->new registroNoExistenteExcepion("Rol no encontrado con id: " + id));
    }
    @Transactional(readOnly = true)
    public RolesModels findByNombre(String nombre) {
        return iRolesRespository.findByNombre(nombre)
                .orElseThrow(()->new registroNoExistenteExcepion("Rol no encontrado con nombre: " + nombre));
    }
    //por LISTA

    //************* ALTA ➕


    //************* BAJA ❌


    //************* MODIFICACION 🔄️
}
