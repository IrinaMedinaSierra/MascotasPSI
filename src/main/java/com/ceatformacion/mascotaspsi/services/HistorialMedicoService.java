package com.ceatformacion.mascotaspsi.services;

import com.ceatformacion.mascotaspsi.model.HistorialMedico;
import com.ceatformacion.mascotaspsi.repository.HistorialMedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HistorialMedicoService {
    private final HistorialMedicoRepository historialRepo;

    public HistorialMedicoService(HistorialMedicoRepository historialRepo) {
        this.historialRepo = historialRepo;
    }

    public List<HistorialMedico> obtenerHistorialPorMascota(Integer idMascota) {
        return historialRepo.findByMascotaIdMascota(idMascota);
    }

    public HistorialMedico guardarEntrada(HistorialMedico historial) {
        return historialRepo.save(historial);
    }

    public void eliminarEntrada(Integer id) {
        historialRepo.deleteById(id);
    }
}
