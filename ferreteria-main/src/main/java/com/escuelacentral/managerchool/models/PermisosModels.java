package com.escuelacentral.managerchool.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Builder
@Entity
@Table(name = "permisos") //se le informa como se llama la tabla en la BD
@AllArgsConstructor
@NoArgsConstructor //contructor sin argumentos
@ToString
@Getter @Setter
public class PermisosModels implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "permisos")
    private List<RolesModels> roles;

    @Override
    public String getAuthority() {
        return this.getNombre();
    }
}
