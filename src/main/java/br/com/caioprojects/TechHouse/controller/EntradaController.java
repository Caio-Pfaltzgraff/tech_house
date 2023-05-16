package br.com.caioprojects.TechHouse.controller;

import br.com.caioprojects.TechHouse.dto.entrada.DadosCadastroEntrada;
import br.com.caioprojects.TechHouse.dto.entrada.DadosEntradaProduto;
import br.com.caioprojects.TechHouse.model.entrada.Entrada;
import br.com.caioprojects.TechHouse.model.entrada.ItemEntrada;
import br.com.caioprojects.TechHouse.model.entrada.ItemEntradaId;
import br.com.caioprojects.TechHouse.model.produto.Produto;
import br.com.caioprojects.TechHouse.repository.EntradaRepository;
import br.com.caioprojects.TechHouse.repository.ItemEntradaRepository;
import br.com.caioprojects.TechHouse.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;

@Controller
@RequestMapping("/entrada")
public class EntradaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ItemEntradaRepository itemEntradaRepository;

    @GetMapping("/formulario")
    public String formulario(DadosCadastroEntrada dados, Model model) {
        var produtos = produtoRepository.findAll().stream().map(DadosEntradaProduto::new).sorted(Comparator.comparing(DadosEntradaProduto::nomeProduto)).toList();

        model.addAttribute("listaProdutos", produtos);

        return "produto/formEntrada";
    }

    @PostMapping("/novo")
    @Transactional
    public String novo(@Valid DadosCadastroEntrada dados) {

        //ver se tem no banco alguma entrada na data colocada
        if(entradaRepository.findByData(dados.dataEntrada()) != null) {
            System.out.println("Entrou no if");
            var entrada = entradaRepository.findByData(dados.dataEntrada());
            var itemEntrada = new ItemEntrada(new ItemEntradaId(entrada.getNumero(), dados.id()), dados.quantidadeProduto());
            itemEntradaRepository.save(itemEntrada);
            Produto produto = produtoRepository.findById(dados.id()).stream().toList().get(0);
            produto.adicionaEstoque(dados.quantidadeProduto());
            return "redirect:/inventory";
        }

        System.out.println("Fora do if");
        var entradaId = entradaRepository.save(new Entrada(dados)).getNumero();
        var itemEntrada = new ItemEntrada(new ItemEntradaId(entradaId, dados.id()), dados.quantidadeProduto());
        itemEntradaRepository.save(itemEntrada);
        Produto produto = produtoRepository.findById(dados.id()).stream().toList().get(0);
        produto.adicionaEstoque(dados.quantidadeProduto());

        //redirecionar a entrada de produto
        return "redirect:/inventory";
    }

}
