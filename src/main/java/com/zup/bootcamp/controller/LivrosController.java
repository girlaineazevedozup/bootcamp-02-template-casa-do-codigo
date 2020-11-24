package com.zup.bootcamp.controller;

import com.zup.bootcamp.model.Livro;
import com.zup.bootcamp.request.LivroRequest;
import com.zup.bootcamp.response.DetalheLivroResponse;
import com.zup.bootcamp.response.LivroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LivrosController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/livro")
    @Transactional
    public String criaLivro(@Valid @RequestBody LivroRequest livroRequest) {
        Livro novoLivro = livroRequest.toModel(manager);
        manager.persist(novoLivro);
        return novoLivro.toString();
    }

    @GetMapping("/livros")
    @Transactional
    public List<LivroResponse> listaLivros(){
        List<Livro> listaLivros = manager.createQuery("SELECT l FROM Livro l").getResultList();

        List<LivroResponse> listaLivrosResponse = listaLivros.stream().map(livro ->
                new LivroResponse(livro.getId(), livro.getTitulo())).collect(Collectors.toList());

        return listaLivrosResponse;
    }

    @GetMapping("/livro/{id}")
    @Transactional
    public ResponseEntity<?> detalhaLivro(@PathVariable("id") Long id){
        //1
        Livro livro = manager.find(Livro.class, id);
        //1
        if(livro == null)
            return ResponseEntity.notFound().build();
        //1
        DetalheLivroResponse detalheLivroResponse = new DetalheLivroResponse(livro);

        return ResponseEntity.ok(detalheLivroResponse);
    }

}
