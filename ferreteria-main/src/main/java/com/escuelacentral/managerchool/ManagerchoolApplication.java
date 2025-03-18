package com.escuelacentral.managerchool;

import com.escuelacentral.managerchool.models.*;
import com.escuelacentral.managerchool.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.escuelacentral.managerchool"})
public class ManagerchoolApplication {
    public static Logger logger = LoggerFactory.getLogger(ManagerchoolApplication.class);

    public static void main(String[] args) {
		logger.debug("Ingresando");
		SpringApplication.run(ManagerchoolApplication.class,args);

	}
	/*@Bean
	CommandLineRunner init(IPersonasRepository personasRepository, IRolesRespository rolesRepository, IPermisosRepository permisosRepository){
		return args ->{
			//***** AGREGANDO PERMISOS
			PermisosModels listarProductos = PermisosModels.builder()
					.nombre("Listar Productos")
					.build();
			PermisosModels CargarNuevoProducto = PermisosModels.builder()
					.nombre("Cargar Nuevo Producto")
					.build();
			PermisosModels EliminarProducto = PermisosModels.builder()
					.nombre("Eliminar Productos")
					.build();
			PermisosModels EditarProducto = PermisosModels.builder()
					.nombre("Editar Producto")
					.build();
			PermisosModels registrarUsuario = PermisosModels.builder()
					.nombre("Registrar Usuario")
					.build();
			permisosRepository.saveAll(Arrays.asList(listarProductos, CargarNuevoProducto, EliminarProducto, EditarProducto, registrarUsuario));

			//***** AGREGANDO ROLES
			RolesModels rolAdministrador=RolesModels.builder()
					.nombre("Administrador")
					.permisos(List.of(listarProductos, CargarNuevoProducto, EliminarProducto, EditarProducto, registrarUsuario))
					.isEnabled(true)
					.build();
			RolesModels rolUsuario=RolesModels.builder()
					.nombre("Usuario")
					.permisos(List.of(listarProductos, CargarNuevoProducto))
					.isEnabled(true)
					.build();
			rolesRepository.saveAll(Set.of(rolAdministrador, rolUsuario));

			Optional<RolesModels> rolModAdministrador= rolesRepository.findByNombre("Administrador");
			Optional<RolesModels> rolModUsuario= rolesRepository.findByNombre("Usuario");

			//***** AGREGANDO PERSONAS
			PersonasModels personaJony= PersonasModels.builder()
					.nombre("JONY")
					.apellido("BOGLIONE")
					.email("jony@prueba.com")
					.enabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.password("0")
					.roles(Set.of(rolModAdministrador.get()))
					.build();
			PersonasModels personaSeba= PersonasModels.builder()
					.nombre("SEBASTIAN")
					.apellido("Tisera")
					.email("seba@prueba.com")
					.enabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.password("0")
					.roles(Set.of(rolModUsuario.get()))
					.build();
			PersonasModels personaJuanito= PersonasModels.builder()
					.nombre("JUANITO")
					.apellido("Lopez")
					.email("juanito@prueba.com")
					.enabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.password("0")
					.roles(Set.of(rolModUsuario.get()))
					.build();
			personasRepository.saveAll(List.of(personaJony,personaSeba,personaJuanito));
		};
	}*/
}
