package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.domain.usuarios.Usuario;
import br.com.caioprojects.TechHouse.domain.usuarios.UsuarioRepository;
import br.com.caioprojects.TechHouse.service.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/logar")
//    public String logar(Model model, String username, String password, HttpServletResponse response) {
//        Usuario user = usuarioRepository.login(username, password);
//        if(user != null) {
//            CookieService.setCookie(response, "usuariosId", String.valueOf(user.getId()), 86400);
//            return "redirect:/produto/inventory";
//        }
//        model.addAttribute("erro", "Usuário ou senha inválidos");
//        return "login";
//    }

}
