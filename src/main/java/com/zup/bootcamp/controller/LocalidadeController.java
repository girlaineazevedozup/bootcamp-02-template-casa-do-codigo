package com.zup.bootcamp.controller;

import com.zup.bootcamp.model.Estado;
import com.zup.bootcamp.model.Pais;
import com.zup.bootcamp.request.EstadoRequest;
import com.zup.bootcamp.request.PaisRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class LocalidadeController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/pais")
    @Transactional
    public String criaPais(@RequestBody @Valid PaisRequest paisRequest){
        Pais novoPais = new Pais(paisRequest.getNome());
        manager.persist(novoPais);
        return novoPais.toString();
    }

    @PostMapping("/estado")
    @Transactional
    public String criaEstado(@RequestBody @Valid EstadoRequest estadoRequest){
        Estado novoEstado = estadoRequest.toModel(manager);
        manager.persist(novoEstado);
        return novoEstado.toString();
    }
}
