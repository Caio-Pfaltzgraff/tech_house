package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.domain.produto.DadosAlteracaoProduto;
import br.com.caioprojects.TechHouse.domain.produto.DadosCadastroProduto;
import br.com.caioprojects.TechHouse.domain.produto.PesquisaProduto;
import br.com.caioprojects.TechHouse.domain.produto.Produto;
import br.com.caioprojects.TechHouse.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/inventory")
    public String todosOsProdutos(Model model) {
        model.addAttribute("produtos", produtoService.findAll());
        model.addAttribute("quantidade", produtoService.quantidadeProdutos());

        return "inventory";
    }

    @GetMapping("/em-falta")
    public String produtosEmFalta(Model model) {
        model.addAttribute("produtos", produtoService.findAllByQuantidadeEstoqueLessThanQuantidadeMinima());
        return "falta";
    }

    @GetMapping("/formulario")
    public String formulario(Long id, Model model) {
        if (id != null) {
            model.addAttribute("produto", produtoService.getReferenceById(id));
        }

        model.addAttribute("tipoProduto", produtoService.tipoProdutos());

        return "produto/formulario";
    }

    @PostMapping
    public String novo(@Valid DadosCadastroProduto dados) {
        produtoService.save(new Produto(dados));

        return "redirect:/produto/inventory";
    }

    @PutMapping
    public String alteraProduto(DadosAlteracaoProduto dados) {
        produtoService.atualiza(dados);

        return "redirect:/produto/inventory";
    }

    @DeleteMapping
    public String removeProduto(Long id) {
        produtoService.delete(id);

        return "redirect:/produto/inventory";
    }

    @PostMapping("/buscas")
    public String pesquisaProdutos(String pesquisa, Model model) {
        var produtos = produtoService.findAll();

        model.addAttribute("produtos", new PesquisaProduto(produtos, pesquisa).realizarPesquisa());
        model.addAttribute("quantidade", -1);

        return "inventory";
    }

}
