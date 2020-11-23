package com.zup.bootcamp.controller;

import com.zup.bootcamp.model.Livro;
import com.zup.bootcamp.request.LivroRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

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
}
