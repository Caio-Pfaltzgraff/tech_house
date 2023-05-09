package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.dto.DadosListagemProdutos;
import br.com.caioprojects.TechHouse.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/home")
    public String home(Model model) {
        List<DadosListagemProdutos> produtos = produtoRepository.findAll().stream().map(DadosListagemProdutos::new).toList();
        model.addAttribute("produtos", produtos);
        return "home";
    }

}
