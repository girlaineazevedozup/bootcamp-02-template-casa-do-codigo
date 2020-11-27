package com.zup.bootcamp.controller;

import com.zup.bootcamp.model.Categoria;
import com.zup.bootcamp.model.Cupom;
import com.zup.bootcamp.request.CategoriaRequest;
import com.zup.bootcamp.request.CupomRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CupomController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/cupom")
    @Transactional
    public String criaCupom(@Valid @RequestBody CupomRequest cupomRequest) {
        Cupom novoCupom = cupomRequest.toModel();
        manager.persist(novoCupom);
        return novoCupom.toString();
    }

}
