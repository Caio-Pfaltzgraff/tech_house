package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.domain.produto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/inventory")
    public String todosOsProdutos(Model model) {
        List<DadosListagemProdutos> produtos = produtoRepository.findAllByAtivoTrue().stream().map(DadosListagemProdutos::new).toList();
        model.addAttribute("produtos", produtos);

        var qtdProdutos = 0;
        for (DadosListagemProdutos produto : produtos) {
            qtdProdutos += produto.quantidadeEstoque();
        }
        model.addAttribute("quantidade", qtdProdutos);

        return "inventory";
    }

    @GetMapping("/em-falta")
    public String produtosEmFalta(Model model) {
        List<DadosProdutosEmFalta> produtos = produtoRepository.findAllByQuantidadeEstoqueLessThanQuantidadeMinima().stream().map(DadosProdutosEmFalta::new).toList();
        model.addAttribute("produtos", produtos);
        return "falta";
    }

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

        return "redirect:/produto/inventory";
    }

    @PutMapping
    @Transactional
    public String alteraProduto(DadosAlteracaoProduto dados) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizaDados(dados);

        return "redirect:/produto/inventory";
    }

    @DeleteMapping
    @Transactional
    public String removeProduto(Long id) {
        var produto = produtoRepository.getReferenceById(id);
        produto.excluir();

        return "redirect:/produto/inventory";
    }

}
