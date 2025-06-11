package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    //necesitar que el repositorio busque aqui por usename
    Optional<Usuario> findByUsername(String username);

}
