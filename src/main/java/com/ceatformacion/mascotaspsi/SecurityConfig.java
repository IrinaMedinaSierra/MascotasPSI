package com.ceatformacion.mascotaspsi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Acceso público
                        .requestMatchers(HttpMethod.GET, "/", "/index", "/img/**").permitAll()

                        // Acceso a /crud: visible para ambos, pero solo Admin puede guardar
                        .requestMatchers(HttpMethod.GET, "/crud").hasAnyRole("User", "Admin")
                        .requestMatchers(HttpMethod.POST, "/crud").hasRole("Admin")

                        // Formularios de gestión de usuarios: solo Admin
                        .requestMatchers(HttpMethod.GET, "/formulario", "/registro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/guardarUsuario").permitAll()

                        // Rutas para editar o borrar: solo Admin
                        .requestMatchers("/editar/**", "/borrar/**").hasRole("Admin")

                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/crud", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encriptación segura
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
