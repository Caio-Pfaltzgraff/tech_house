package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.domain.movimento.*;
import br.com.caioprojects.TechHouse.domain.produto.Produto;
import br.com.caioprojects.TechHouse.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

@Controller
@RequestMapping("/acao")
public class MovimentoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ItemMovimentoRepository itemMovimentoRepository;

    @Autowired
    private EntradaProdutoService entradaProdutoService;

    @GetMapping("/entrada")
    public String carregaFormularioEntrada() {

        return "entrada/formEntrada";
    }

    @PostMapping("/entrada/produtos")
    public String processaFormularioEntrada(Long produtoId, Integer quantidade, Model model) {
        entradaProdutoService.adicionaProduto(produtoId, quantidade);

//        model.addAttribute("entrada", entradaProdutoService.getMovimento());
        model.addAttribute("itens", entradaProdutoService.getMovimento().getItensMovimento());
        model.addAttribute("verifica", entradaProdutoService.getMovimento().getItensMovimento().size() > 0);

       return "entrada/formEntrada";
    }

    @GetMapping("entrada/produto/formulario")
    public String carregaFormularioProduto(Model model) {
        var produtos = produtoRepository.findAll().stream().sorted(Comparator.comparing(Produto::getNome)).toList();
        model.addAttribute("produtos", produtos);

        return "entrada/formProduto";
    }

    @GetMapping("/inventory")
    public String limpaItens() {
        this.entradaProdutoService.getMovimento().getItensMovimento().clear();

        return "redirect:/inventory";
    }

    @GetMapping("/removeProdutos")
    public String limpaProdutosMovimento() {
        this.entradaProdutoService.getMovimento().getItensMovimento().clear();

        return "redirect:/acao/entrada";
    }

    @PostMapping("/salvar")
    @Transactional
    public  String salvaMovimento(LocalDate data) {
        var entrada = entradaProdutoService.criaEntrada(data);
        movimentoRepository.save(entrada);

        for (ItemMovimento item : entrada.getItensMovimento()) {
            item.getId().setNumeroMovimento(entrada.getNumero());
            itemMovimentoRepository.save(item);
            var produto = produtoRepository.getReferenceById(item.getProduto().getId());
            produto.adicionaEstoque(item.getQuantidade());
        }

        return "redirect:/acao/inventory";
    }


}
