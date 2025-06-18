package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.HistorialMedico;
import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.HistorialMedicoRepository;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.services.HistorialMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // 👈 CAMBIADO: para usar vistas Thymeleaf
public class HistorialMedicoController {

    private final HistorialMedicoService historialService;

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public HistorialMedicoController(HistorialMedicoService historialService) {
        this.historialService = historialService;
    }

    // 🔄 API REST (JSON) para historial por ID de mascota
    @ResponseBody
    @GetMapping("/mascota/{id}")
    public List<HistorialMedico> getHistorialByMascota(@PathVariable int id) {
        return historialService.obtenerHistorialPorMascota(id);
    }

    // 🔄 API REST para guardar una nueva entrada (por JSON)
    @ResponseBody
    @PostMapping("/api/historial")
    public HistorialMedico guardar(@RequestBody HistorialMedico entrada) {
        return historialService.guardarEntrada(entrada);
    }

    // 🔄 API REST para eliminar
    @ResponseBody
    @DeleteMapping("/api/historial/{id}")
    public void eliminar(@PathVariable int id) {
        historialService.eliminarEntrada(id);
    }

    // 🔍 Vista: buscar por nombre (desde CRUD)
    @GetMapping("/buscar")
    public String buscarPorNombre(@RequestParam String nombre, Model model) {
        List<Mascotas> resultados = mascotaRepository.findByNombreContainingIgnoreCase(nombre);
        model.addAttribute("mascotaParaCRUD", resultados);
        return "crud"; // Asegúrate de que este archivo existe en templates
    }

    // 👁️ Vista: mostrar formulario e historial de una mascota
    @GetMapping("/consulta/{id}")
    public String verConsultaMascota(@PathVariable int id, Model model) {
        Mascotas mascota = mascotaRepository.findById(id).orElseThrow();
        List<HistorialMedico> historial = historialMedicoRepository.findByMascotaIdMascota(id);

        model.addAttribute("mascota", mascota);
        model.addAttribute("historial", historial);
        model.addAttribute("nuevaVisita", new HistorialMedico());

        return "historialMascota"; // nombre de la plantilla HTML
    }

    // 💾 POST desde formulario: registrar visita
    @PostMapping("/consulta/{id}")
    public String registrarVisita(@PathVariable int id,
                                  @ModelAttribute("nuevaVisita") HistorialMedico nuevaVisita) {
        Mascotas mascota = mascotaRepository.findById(id).orElseThrow();
        nuevaVisita.setMascota(mascota);
        historialMedicoRepository.save(nuevaVisita);

        return "redirect:/consulta/" + id;
    }
}
