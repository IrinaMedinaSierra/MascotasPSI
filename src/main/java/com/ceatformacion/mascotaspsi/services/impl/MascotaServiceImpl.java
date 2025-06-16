package com.ceatformacion.mascotaspsi.services.impl;


import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public Page<Mascotas> listarMascotas(Pageable pageable) {
        return mascotaRepository.findAll(pageable);
    }
}

