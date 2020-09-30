package br.com.gerenciamentoprojetos.controller;

import br.com.gerenciamentoprojetos.model.Funcionario;
import br.com.gerenciamentoprojetos.model.Usuario;
import br.com.gerenciamentoprojetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/list")
    public String listUsuario(Model model) {

        model.addAttribute("usuarios", usuarioRepository.findAll(Sort.by("nome")));
        return "usuario/list";
    }

    @GetMapping("/usuario/add")
    public String addUsuario(Model model) {
        try {
            model.addAttribute("usuario", new Usuario());

        }catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }
        return "usuario/add";
    }

    @GetMapping("/usuario/view/{id}")
    public String viewUsuario(@PathVariable long id, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id));
        return "usuario/view";
    }

    @GetMapping("/usuario/delete/{id}")
    public String deleteUsuario(@PathVariable long id, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id));
        return "usuario/delete";
    }

    @PostMapping("/usuario/save")
    public String saveUsuario(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
        }catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return "redirect:/usuario/view/" + usuario.getId();
    }

    @PostMapping("/usuario/delete")
    public String deleteUsuario(Usuario usuario) {
        try {
            usuarioRepository.delete(usuario);
        }catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return "redirect:/usuario/list";
    }

}
