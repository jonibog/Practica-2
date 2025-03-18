package com.escuelacentral.managerchool.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
@Table(name = "personas") //se le informa como se llama la tabla en la BD
@AllArgsConstructor
@NoArgsConstructor //contructor sin argumentos
@ToString
@Getter @Setter
public class PersonasModels implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean enabled = true; // Si la cuenta está activa
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "persona_roles",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<RolesModels> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}