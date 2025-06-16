package com.ceatformacion.mascotaspsi.controller;
import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.services.MascotaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ceatformacion.mascotaspsi.model.Usuario;
import com.ceatformacion.mascotaspsi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
   @Autowired
    UsuarioRepository usuarioRepository;
   @Autowired
    PasswordEncoder encoder;
   MascotaService mascotaService;

    @GetMapping("/registro")
    public String registrarUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registroUsuario";
    }
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario,Model model) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername(usuario.getUsername());
            user.setPassword(encoder.encode(usuario.getPassword()));
            user.setRol(usuario.getRol());
            usuarioRepository.save(user);
            return "redirect:/";
        }else{
            model.addAttribute("error", "El usuario ya existe, indique uno nuevo");
            return "registroUsuario";
        }

    }


    //Paginacion
    @GetMapping("/crud-user")
    public String verMascotas(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Mascotas> mascotasPage = mascotaService.listarMascotas(PageRequest.of(page, 5)); // 5 por página
        model.addAttribute("mascotaParaCRUD", mascotasPage.getContent());
        model.addAttribute("totalPages", mascotasPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "crud";
    }



}
