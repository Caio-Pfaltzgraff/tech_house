package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.domain.movimento.*;
import br.com.caioprojects.TechHouse.domain.produto.Produto;
import br.com.caioprojects.TechHouse.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    @Autowired
    private SaidaProdutoService saidaProdutoService;

    @GetMapping("/entrada")
    public String carregaFormularioEntrada() {

        return "entrada/formEntrada";
    }

    @GetMapping("/saida")
    public String carregaFormularioSaida() {

        return "saida/formSaida";
    }

    @PostMapping("/entrada/produtos")
    public String processaFormularioEntrada(Long produtoId, Integer quantidade, Model model) {
        entradaProdutoService.adicionaProduto(produtoId, quantidade);

        model.addAttribute("itens", entradaProdutoService.getMovimento().getItensMovimento());
        model.addAttribute("verifica", entradaProdutoService.getMovimento().getItensMovimento().size() > 0);

       return "entrada/formEntrada";
    }

    @PostMapping("/saida/produtos")
    public String processaFormularioSaida(Long produtoId, Integer quantidade, Model model) {
        saidaProdutoService.adicionaProduto(produtoId, quantidade);

        model.addAttribute("itens", saidaProdutoService.getMovimento().getItensMovimento());
        model.addAttribute("verifica", saidaProdutoService.getMovimento().getItensMovimento().size() > 0);

        return "saida/formSaida";
    }

    @GetMapping("entrada/produto/formulario")
    public String carregaFormularioProduto(Model model) {
        var produtos = produtoRepository.findAll().stream().sorted(Comparator.comparing(Produto::getNome)).toList();
        model.addAttribute("produtos", produtos);

        return "entrada/formProduto";
    }

    @GetMapping("saida/produto/formulario")
    public String carregaFormularioProdutoSaida(Model model) {
        var listaProdutos = produtoRepository.findAll().stream().sorted(Comparator.comparing(Produto::getNome)).toList();
        List<Produto> produtos = new ArrayList<>();
        for (Produto produto : listaProdutos) {
            if (produto.getQuantidadeEstoque() > 0) {
                produtos.add(produto);
            }
        }
        model.addAttribute("produtos", produtos);

        return "saida/formProdutoSaida";
    }

    @GetMapping("/inventory")
    public String limpaItens() {
        this.entradaProdutoService.getMovimento().getItensMovimento().clear();
        this.saidaProdutoService.getMovimento().getItensMovimento().clear();

        return "redirect:/produto/inventory";
    }

    @GetMapping("/removeProdutos")
    public String limpaProdutosMovimento() {
        this.entradaProdutoService.getMovimento().getItensMovimento().clear();
        this.saidaProdutoService.getMovimento().getItensMovimento().clear();

        return "redirect:/acao/entrada";
    }

    @PostMapping("/salvar")
    @Transactional
    public  String salvaEntrada(LocalDate data) {
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

    @PostMapping("/salva")
    @Transactional
    public  String salvaSaida() {
        var saida = saidaProdutoService.criaSaida();
        movimentoRepository.save(saida);

        for (ItemMovimento item : saida.getItensMovimento()) {
            item.getId().setNumeroMovimento(saida.getNumero());
            itemMovimentoRepository.save(item);
            var produto = produtoRepository.getReferenceById(item.getProduto().getId());
            produto.saidaEstoque(item.getQuantidade());
        }

        return "redirect:/acao/inventory";
    }


}
