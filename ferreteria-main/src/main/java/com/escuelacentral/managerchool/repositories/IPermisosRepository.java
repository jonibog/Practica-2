package com.escuelacentral.managerchool.repositories;

import com.escuelacentral.managerchool.models.PermisosModels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermisosRepository extends CrudRepository<PermisosModels, Integer> {
}
