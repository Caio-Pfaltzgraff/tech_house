package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.dto.produto.DadosCadastroProduto;
import br.com.caioprojects.TechHouse.model.produto.Produto;
import br.com.caioprojects.TechHouse.model.produto.TipoProduto;
import br.com.caioprojects.TechHouse.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/formulario")
    public String formulario(DadosCadastroProduto dados, Model model) {
        var tipoProduto = Arrays.stream(TipoProduto.values()).toList();
        model.addAttribute("tipoProduto", tipoProduto);

        return "produto/formulario";
    }

    @PostMapping("novo")
    @Transactional
    public String novo(@Valid DadosCadastroProduto dados) {
        produtoRepository.save(new Produto(dados));

        return "redirect:/inventory";
    }

}
