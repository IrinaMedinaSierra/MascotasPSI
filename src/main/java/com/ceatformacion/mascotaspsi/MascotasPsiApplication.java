package com.ceatformacion.mascotaspsi;

import com.ceatformacion.mascotaspsi.model.Usuario;
import com.ceatformacion.mascotaspsi.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MascotasPsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MascotasPsiApplication.class, args);
    }

}
