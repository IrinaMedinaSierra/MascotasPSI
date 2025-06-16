package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.repository.UsuarioRepository;
import com.ceatformacion.mascotaspsi.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/formulario")
    public String mostrarformulario(Model model) {
        model.addAttribute("mascota",new Mascotas());
        return "formulario";
    }

    @PostMapping("/crud")
    public String leerFormulario(@ModelAttribute Mascotas mascotas, Model model) {
        mascotaRepository.save(mascotas);
        return "redirect:/crud";
    }

/*    @GetMapping("/crud")
    public String mostrarMascotas(Model model) {
        model.addAttribute("mascotaParaCRUD",mascotaRepository.findAll());
        return "crud";
    }*/

   @GetMapping("/editar/{idMascotas}")
    public String editarMascota(Model model, @PathVariable int idMascotas) {
        Mascotas mascota = mascotaRepository.findById(idMascotas).get();
        model.addAttribute("mascota",mascota);
        return "formulario";
    }

    @GetMapping("/borrar/{idMascotas}")
    public String borrarMascota(Model model, @PathVariable int idMascotas) {
        mascotaRepository.deleteById(idMascotas);
        return "redirect:/crud";
    }

    @GetMapping("/crud")
    public String verMascotas(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Mascotas> mascotasPage = mascotaService.listarMascotas(PageRequest.of(page, 5)); // 5 por página
        model.addAttribute("mascotaParaCRUD", mascotasPage.getContent());
        model.addAttribute("totalPages", mascotasPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "crud";
    }


}
