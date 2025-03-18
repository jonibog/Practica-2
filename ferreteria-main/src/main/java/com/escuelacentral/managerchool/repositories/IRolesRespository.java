package com.escuelacentral.managerchool.repositories;

import com.escuelacentral.managerchool.models.RolesModels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRespository extends CrudRepository<RolesModels, Integer> {
    boolean existsByNombre(String nombre);
    Optional<RolesModels> findByNombre(String nombre);
}
