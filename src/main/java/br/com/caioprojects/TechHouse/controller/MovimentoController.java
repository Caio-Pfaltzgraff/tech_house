package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.domain.movimento.EntradaProdutoService;
import br.com.caioprojects.TechHouse.services.MovimentoService;
import br.com.caioprojects.TechHouse.domain.movimento.SaidaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/acao")
public class MovimentoController {

    @Autowired
    private MovimentoService movimentoService;

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
       model.addAttribute("produtos", movimentoService.buscaProdutosEntrada());

        return "entrada/formProduto";
    }

    @GetMapping("saida/produto/formulario")
    public String carregaFormularioProdutoSaida(Model model) {
        model.addAttribute("produtos", movimentoService.buscaProdutosSaida());

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
    public  String salvaEntrada(LocalDate data) {
        var entrada = entradaProdutoService.criaEntrada(data);
        movimentoService.save(entrada);

        return "redirect:/acao/inventory";
    }

    @PostMapping("/salva")
    public  String salvaSaida() {
        var saida = saidaProdutoService.criaSaida();
        movimentoService.save(saida);

        return "redirect:/acao/inventory";
    }


}
