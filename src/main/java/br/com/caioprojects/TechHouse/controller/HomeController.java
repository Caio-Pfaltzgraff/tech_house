package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.dto.DadosProdutosEmFalta;
import br.com.caioprojects.TechHouse.dto.DadosListagemProdutos;
import br.com.caioprojects.TechHouse.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String todosOsProdutos(Model model) {
        List<DadosListagemProdutos> produtos = produtoRepository.findAll().stream().map(DadosListagemProdutos::new).toList();
        model.addAttribute("produtos", produtos);
        return "home";
    }

    @GetMapping("/produtos-em-falta")
    public String produtosEmFalta(Model model) {
        List<DadosProdutosEmFalta> produtos = produtoRepository.findAllByQuantidadeEstoqueLessThanQuantidadeMinima().stream().map(DadosProdutosEmFalta::new).toList();
        model.addAttribute("produtos", produtos);
        return "falta";
    }

}
