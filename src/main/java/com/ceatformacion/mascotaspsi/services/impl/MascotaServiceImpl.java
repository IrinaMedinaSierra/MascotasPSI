package com.ceatformacion.mascotaspsi.services.impl;


import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Anotación que indica que esta clase es un componente de servicio en Spring
// Será detectada automáticamente y registrada como un bean
@Service
public class MascotaServiceImpl implements MascotaService {

    // Inyección automática del repositorio de mascotas (DAO)
    @Autowired
    private MascotaRepository mascotaRepository;

    // Implementación del método definido en la interfaz MascotaService
    // Este método devuelve una página de resultados de mascotas según el objeto Pageable
    @Override
    public Page<Mascotas> listarMascotas(Pageable pageable) {
        // Llama al método findAll del repositorio, que devuelve una Page con las mascotas
        return mascotaRepository.findAll(pageable);
    }
}
