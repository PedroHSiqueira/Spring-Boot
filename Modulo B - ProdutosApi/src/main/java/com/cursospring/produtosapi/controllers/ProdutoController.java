package com.cursospring.produtosapi.controllers;

import com.cursospring.produtosapi.model.Produto;
import com.cursospring.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
     public Produto salvar(@RequestBody Produto produto){
         String id = UUID.randomUUID().toString();
         produto.setId(id);
         System.out.println("Produto Recebido: " + produto);
         produtoRepository.save(produto);
         return produto;
     }

     @GetMapping("/{id}")
     public Produto obterPorId(@PathVariable("id") String id){
        return produtoRepository.findById(id).orElse(null);
     }

     @DeleteMapping("/{id}")
     public void deletar(@PathVariable("id") String id){
        produtoRepository.deleteById(id);
     }

     @PutMapping("/{id}")
     public void atualizar(@PathVariable String id, @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
     }

     @GetMapping
     public List<Produto> busca(@RequestParam("nome") String nome){
       return produtoRepository.findByNome(nome);
     }
}
