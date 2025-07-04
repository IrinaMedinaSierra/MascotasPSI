package com.ceatformacion.mascotaspsi.services;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MascotaService {
    Page<Mascotas> listarMascotas(Pageable pageable);
}
