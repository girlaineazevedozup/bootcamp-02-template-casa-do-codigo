package com.zup.bootcamp.controller;

import com.zup.bootcamp.model.Categoria;
import com.zup.bootcamp.request.CategoriaRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/categoria")
    @Transactional
    public String criaCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        Categoria novaCategoria = categoriaRequest.toModel();
        manager.persist(novaCategoria);
        return novaCategoria.toString();
    }
}
