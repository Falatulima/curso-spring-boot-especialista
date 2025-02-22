package io.github.cursoSpringBoot.produtosapi.controller;

import io.github.cursoSpringBoot.produtosapi.model.Produto;
import io.github.cursoSpringBoot.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController //Marca a classe para ser um controlador rest(receber requisições)
@RequestMapping("produtos") //Qual é a url base desse controle
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping //Requisição post(criar um recurso)
    public Produto salvar(@RequestBody Produto produto) { //RequestBody serve para que a requisição do post insira as informações no body
        System.out.println("Produto recebido: " + produto);

        var id = UUID.randomUUID().toString(); //gerando código do id
        produto.setId(id); //inserinddo o código gerado no id do produto

        produtoRepository.save(produto); //salvando as informações do produto no banco
        return produto;
    }

    @PostMapping("/lote")
    public List<Produto> salvarMultiplos(@RequestBody List<Produto> produtos) {
        produtos.forEach(produto -> {
            if (produto.getId() == null || produto.getId().isEmpty()) {
                produto.setId(UUID.randomUUID().toString());
            }
        });

        return produtoRepository.saveAll(produtos);
    }

    @GetMapping("/{id}") //Requisição Get(solicitar um recurso)
    public Produto obterProdutoId(@PathVariable("id") String id) {
        //Optional<Produto> byId = produtoRepository.findById(id);
        //return produto.isPresent() ? produto.get() : null;

        //outra forma de fazer
        return produtoRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @DeleteMapping("/{id}") //Requisição Get(solicitar um recurso)
    public void deletarProdutoId(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id,
                          @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }
}
