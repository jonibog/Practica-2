package com.escuelacentral.managerchool.repositories;

import com.escuelacentral.managerchool.models.PersonasModels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Los repositorios clase que permiten hacer consultas a una Base de datos
@Repository
public interface IPersonasRepository extends CrudRepository<PersonasModels, Integer> {
    boolean existsByEmail(String email);
    Optional<PersonasModels> findByEmail(String email);
    List<PersonasModels> findAllByEnabledTrue();

}
