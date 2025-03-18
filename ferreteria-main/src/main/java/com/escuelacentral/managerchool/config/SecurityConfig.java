package com.escuelacentral.managerchool.config;

import com.escuelacentral.managerchool.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity //Se utiliza para asignar anotaciones en los controles para esppesificar la seguridad. (https://www.youtube.com/watch?v=IPWBQDMIYkc&t=520s)
//@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler; //Manejador de Errores personalizado en login

    // Configuración de seguridad HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitamos CSRF para simplificar en este caso
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)) //habilita las sesiones y permite que el usuario autenticado mantenga el acceso a recursos privados.
                //.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Desactiva la creación de sesiones
                .authorizeHttpRequests(auth -> auth
                        //Configurar los endpoints publicos
                        .requestMatchers(
                                "/login",
                                "/styles/**",
                                "/js/**",
                                "/css/**",
                                "/img/**",
                                "/images/**",
                                "/uploads/**").permitAll()  // Páginas públicas sin necesidad de autenticación
                        //Configurar los endpoints Privados
                        .requestMatchers("/home/**").authenticated()  // Cualquier otra solicitud requiere autenticacións
                        .requestMatchers("/acciones/**").authenticated()  // Cualquier otra solicitud requiere autenticación
                        .requestMatchers("/api/v1/**").authenticated()
                        .requestMatchers("/api/v1/personas").permitAll()
                        .requestMatchers("/registrar/**").hasRole("Administrador")
                        //Configurar el resto de endpoints - NO ESPESIFICADOS.
                        //.anyRequest().authenticated()  // Cualquier otra solicitud requiere autenticación
                        .anyRequest().denyAll()  // Rechazar cualquier solicitud, aun si esta logiado por no espesificar el endpoint
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página personalizada de login
                        .permitAll()// Todos pueden acceder a la página de login
                        .usernameParameter("email")
                        .passwordParameter("psw")
                        .failureHandler(customAuthenticationFailureHandler) // Aquí manejamos los errores de login
                        .defaultSuccessUrl("/home", true)// Redirecciona al /home después de un login exitoso, `true` forzar redirección a esta URL
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")// URL para hacer logout
                        .logoutSuccessUrl("/login?logout=true")// Redirecciona después de logout
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean //Proveedor para autentificacion por medio de la Base de Datos
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(userDetailsService);
        return auth;
    }

    // Configuración del cifrado de contraseña
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Cambiar al momento de subir a producción, ya que no encripta.
        return NoOpPasswordEncoder.getInstance();
    }
}