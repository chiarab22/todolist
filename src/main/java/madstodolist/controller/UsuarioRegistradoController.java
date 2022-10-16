package madstodolist.controller;

import madstodolist.controller.exception.UsuarioNoLogeadoException;
import madstodolist.model.Usuario;
import madstodolist.model.UsuarioRepository;
import madstodolist.service.TareaService;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsuarioRegistradoController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TareaService tareaService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/registrados")
    public String usuarioRegistrado(Model model) {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        model.addAttribute("usuario", usuarios);
        return "usuariosRegistrados";
    }

    @GetMapping("/registrados/{id}")
    public String UsuarioRegistradoById(@PathVariable(value="id") Long idUsuario, Model model,  HttpSession session){
    Usuario usuarioId = usuarioService.findById(idUsuario);
    if(usuarioId == null){
        throw new UsuarioNoLogeadoException();
    }
    return "descripcionUsuario";
    }
}


