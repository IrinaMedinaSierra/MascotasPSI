package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialMedicoRepository  extends JpaRepository<HistorialMedico, Integer> {
    List<HistorialMedico> findByMascotaIdMascota(Integer idMascota);

}
