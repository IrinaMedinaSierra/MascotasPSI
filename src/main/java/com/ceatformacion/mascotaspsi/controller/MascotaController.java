package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

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

    @GetMapping("/crud")
    public String mostrarMascotas(Model model) {
        model.addAttribute("mascotaParaCRUD",mascotaRepository.findAll());
        return "crud";
    }

//    @GetMapping("/editar/{idMascotas}")
//    public String editarMascota(Model model, @PathVariable int idMascotas) {
//        Mascotas mascota = mascotaRepository.findById(idMascotas).get();
//        model.addAttribute("Mascota",mascota);
//        return "formulario";
//    }
//
//    @GetMapping("/borrar/{idMascotas}")
//    public String borrarMascota(Model model, @PathVariable int idMascotas) {
//        mascotaRepository.deleteById(idMascotas);
//        return "redirect:/crud";
//    }
}
