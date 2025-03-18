package com.escuelacentral.managerchool.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Table(name = "roles") //se le informa como se llama la tabla en la BD
@AllArgsConstructor
@NoArgsConstructor //contructor sin argumentos
@ToString
@Getter @Setter
public class RolesModels implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String nombre;
    private boolean isEnabled;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<PersonasModels> personas;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rol_permisos",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    private List<PermisosModels> permisos;

    @Override
    public String getAuthority() {
        return this.getNombre();
    }
}
