package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.domain.produto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/formulario")
    public String formulario(Long id, Model model) {
        if (id != null) {
            var produto = produtoRepository.getReferenceById(id);
            model.addAttribute("produto", produto);
        }

        var tipoProduto = Arrays.stream(TipoProduto.values()).toList();
        model.addAttribute("tipoProduto", tipoProduto);

        return "produto/formulario";
    }

    @PostMapping
    @Transactional
    public String novo(@Valid DadosCadastroProduto dados) {
        produtoRepository.save(new Produto(dados));

        return "redirect:/inventory";
    }

    @PutMapping
    @Transactional
    public String alteraProduto(DadosAlteracaoProduto dados) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizaDados(dados);

        return "redirect:/inventory";
    }

    @DeleteMapping
    @Transactional
    public String removeProduto(Long id) {
        var produto = produtoRepository.getReferenceById(id);
        produto.excluir();

        return "redirect:/inventory";
    }

}
